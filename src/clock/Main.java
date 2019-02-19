package clock;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main class. Creating the clock.
 * 
 * @author joanna Czernicka
 * @since February 2019
 *
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		// Create the GUI on the event-dispatching thread
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ClockWindow();
			}
		});
	}
}
