package starField;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A single star with a 3D location.
 * 
 * @author Gage Davidson
 */
class Star {
	
	// half the width of the screen
	private static final int HALF_WIDTH = Display.SCREEN_WIDTH / 2;
	// half the height of the screen
	private static final int HALF_HEIGHT = Display.SCREEN_HEIGHT / 2;
	
	private static final int SELECTION_BOX_SIZE = 20;
	
	// star location
	private float x, y;
	private float[] z;
	private Rectangle selectionBox;
	private boolean selected;
	
	Star() {
		z = new float[2];
		init();
	}
	
	/**
	 * Moves the star closer to the screen.
	 */
	void update(float speed, Point mousePoint) {
		z[1] = z[0];
		z[0] -= speed;
		
		// if the star moved past the screen, re-initialize it
		if (z[0] < 0)
			init();
		
		selectionBox = makeSelectionBox();
		selected = selectionBox.contains(mousePoint);
	}
	
	/**
	 * Draws the star.
	 * @param g Graphics to use
	 */
	void draw(Graphics g) {
		int screenX1 = (int) (x / z[0] * HALF_WIDTH + HALF_WIDTH);
		int screenY1 = (int) (y / z[0] * HALF_HEIGHT + HALF_HEIGHT);
		int screenX2 = (int) (x / z[1] * HALF_WIDTH + HALF_WIDTH);
		int screenY2 = (int) (y / z[1] * HALF_HEIGHT + HALF_HEIGHT);
		
		g.drawLine(screenX1, screenY1, screenX2, screenY2);
	}
	
	private Rectangle makeSelectionBox() {
		return new Rectangle(
				(int) (x / z[0] * HALF_WIDTH + HALF_WIDTH) - SELECTION_BOX_SIZE / 2,
				(int) (y / z[0] * HALF_HEIGHT + HALF_HEIGHT) - SELECTION_BOX_SIZE / 2,
				SELECTION_BOX_SIZE, SELECTION_BOX_SIZE);
	}
	
	Rectangle getSelectionBox() {
		return selectionBox;
	}
	
	boolean isSelected() {
		return selected;
	}
	
	/**
	 * Initialize the star with random coordinates.
	 */
	private void init() {
		x = (float) (Math.random() * 2 - 1);
		y = (float) (Math.random() * 2 - 1);
		z[0] = (float) (Math.random() * 3f);
		z[1] = z[0];
	}
}
