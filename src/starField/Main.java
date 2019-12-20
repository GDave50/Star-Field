package starField;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * This application simulates flying through space in a star field.
 * It runs fullscreen on the primary monitor.
 * @author Gage Davidson
 */
class Main {
	
	/**
	 * @param commandLine command line arguments (unused)
	 */
	public static void main(String[] commandLine) {
		Stars starField = new Stars();
		
		Display display = new Display() {
			@Override
			void draw(Graphics g) {
				starField.draw(g);
			}
		};
		
		// add keyboard and mouse control
		Control control = new Control();
		display.addKeyListener(control);
		display.addMouseMotionListener(control);
		
		// start display clock
		display.run();
		
		// start logic clock
		new Timer(1000 / 25, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (control.up())
					starField.speedUp();
				else if (control.down())
					starField.slowDown();
				
				starField.update(control.mousePoint());
			}
		}).start();
	}
}
