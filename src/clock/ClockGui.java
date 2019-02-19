package clock;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Main Panel with clock's graphic and position of elements.
 * 
 * @author joanna Czernicka
 *
 */
public class ClockGui extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L; // default
	private Image background;
	private BufferedImage hourArrow;
	private BufferedImage minuteArrow;
	private BufferedImage secondArrow;

	private Thread thread;

	ClockGui() {
		setOpaque(false);
		loadImages();
		thread = new Thread(this, "Clock");
		thread.start();
	}

	private void loadImages() {
		try {
			background = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/bckg.png"));
			hourArrow = ImageIO.read(getClass().getResource("/res/hour.png"));
			minuteArrow = ImageIO.read(getClass().getResource("/res/minute.png"));
			secondArrow = ImageIO.read(getClass().getResource("/res/second.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// background
		g.drawImage(background, 0, 0, this);

		// second arrow
		double rotationRequired = Math.toRadians(Logic.getSecondRot());
		AffineTransformOp op = rotateImage(rotationRequired);
		g.drawImage(op.filter(secondArrow, null), 0, 0, null);

		// minute arrow
		rotationRequired = Math.toRadians(Logic.getMinuteRot());
		op = rotateImage(rotationRequired);
		g.drawImage(op.filter(minuteArrow, null), 0, 0, null);

		// hour arrow
		rotationRequired = Math.toRadians(Logic.getHourRot());
		op = rotateImage(rotationRequired);
		g.drawImage(op.filter(hourArrow, null), 0, 0, null);

	}

	private AffineTransformOp rotateImage(double rotationRequired) {
		double locationX = ClockWindow.getWindowWidth() / 2;
		double locationY = ClockWindow.getWindowHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op;
	}

	@Override
	public void run() {
		while (true) {
			new Logic();
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
