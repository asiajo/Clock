package clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JFrame of the clock and its properties.
 * 
 * @author joanna Czernicka
 *
 */
public class ClockWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int mousePositionX, mousePositionY;
	private static int windowWidth = 600;
	private static int windowHeight = 600;

	public ClockWindow() {

		windowMoving();

		// setting the main frame
		setLocationRelativeTo(null);
		setSize(windowWidth, windowHeight);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		getRootPane().setOpaque(false);

		// setting closing button
		JButton close = new JButton();
		close.setOpaque(false);
		close.setContentAreaFilled(false);
		close.setBorderPainted(false);
		try {
			ImageIcon img = new ImageIcon(getClass().getResource("/res/close.png"));
			close.setIcon(img);
		} catch (Exception e) {
			System.out.println("button " + e);
		}
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		// adding closing button at the top
		JPanel top = new JPanel();
		top.setBackground(new Color(0, 0, 0, 0));
		top.setOpaque(false);
		top.add(Box.createRigidArea(new Dimension(windowWidth - 100, 50)));
		top.add(close);

		// adding clock
		ClockGui clock = new ClockGui();
		clock.add(top);
		add(clock);
	}

	/**
	 * Implements possibility of relocation of the JFrame while it is dragged.
	 */
	private void windowMoving() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				mousePositionX = mouseEvent.getPoint().x;
				mousePositionY = mouseEvent.getPoint().y;
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				mousePositionX = mouseEvent.getPoint().x;
				mousePositionY = mouseEvent.getPoint().y;
			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent) {
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				int newFramePositionX = (mouseEvent.getPoint().x - mousePositionX) + getLocation().x;
				int newFramePositionY = (mouseEvent.getPoint().y - mousePositionY) + getLocation().y;
				setLocation(newFramePositionX, newFramePositionY);
			}
		});
	}

	public static int getWindowWidth() {
		return windowWidth;
	}

	public static int getWindowHeight() {
		return windowHeight;
	}
}