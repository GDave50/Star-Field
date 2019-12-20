package starField;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * An array of stars that make up the star field.
 * @author Gage Davidson
 */
class Stars {
	
	private static final int STAR_COUNT = 1000;
	private static final float SPEED_DELTA = .0002f;
	
	private Star[] stars;
	
	// z delta, applied each frame
	private float starSpeed = .001f;
	
	Stars() {
		// initialize the star field
		stars = new Star[STAR_COUNT];
		for (int i = 0; i < stars.length; ++i)
			stars[i] = new Star();
	}
	
	/**
	 * Moves all the stars in the star field.
	 */
	synchronized void update(Point mousePoint) {
		for (Star star : stars)
			star.update(starSpeed, mousePoint);
	}
	
	void speedUp() {
		starSpeed += SPEED_DELTA;
	}
	
	void slowDown() {
		starSpeed -= SPEED_DELTA;
	}
	
	/**
	 * Draws the star field.
	 * @param g Graphics to use
	 */
	synchronized void draw(Graphics g) {
		g.setColor(Display.FOREGROUND_COLOR);
		for (Star star : stars)
			star.draw(g);
		
		g.setColor(Color.RED);
		for (Star star : stars) {
			if (star.isSelected()) {
				Rectangle selectionBox = star.getSelectionBox();
				g.drawOval(selectionBox.x, selectionBox.y,
						selectionBox.width, selectionBox.height);
			}
		}
	}
}
