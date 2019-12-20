package starField;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * Display used for displaying the star field.
 * @author Gage Davidson
 */
abstract class Display extends JPanel implements Runnable {
	
	static final Dimension SCREEN_RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
	static final int SCREEN_WIDTH = 1920 / 2;
	static final int SCREEN_HEIGHT = (int) ((float) SCREEN_WIDTH / 16 * 9);
	
	static final int DISPLAY_FPS = 25;
	static final String DISPLAY_TITLE = "Star Field";
	static final Color FOREGROUND_COLOR = Color.WHITE; // star color
	static final Color BACKGROUND_COLOR = Color.BLACK; // space color
	
	static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(
			new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
			new Point(0, 0), "blank cursor");
	
	private final JFrame frame;
	private final BufferedImage screenImage;
	private final Graphics screenGraphics;
	
	Display() {
		setPreferredSize(SCREEN_RESOLUTION);
//		setCursor(BLANK_CURSOR); // no cursor will appear on the window
		
		// pressing escape quits the application
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
			}
		});
		
		frame = new JFrame(DISPLAY_TITLE);
		frame.setContentPane(this);
		frame.setResizable(false);
		frame.setUndecorated(true); // no window border or buttons
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		screenImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		screenGraphics = screenImage.getGraphics();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
		}
		
		requestFocus();
	}
	
	@Override
	public void run() {
		new Timer(1000 / Display.DISPLAY_FPS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		}).start();
	}
	
	/**
	 * Called when repaint() is called on the display.
	 */
	@Override
	public void paintComponent(Graphics g) {
		screenGraphics.setColor(BACKGROUND_COLOR);
		screenGraphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		draw(screenGraphics);
		
		g.drawImage(screenImage, 0, 0,
				frame.getContentPane().getWidth(),
				frame.getContentPane().getHeight(),
				null);
	}
	
	/**
	 * Draws on the panel.
	 * @param g Graphics to draw with
	 */
	abstract void draw(Graphics g);
}
