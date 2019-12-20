package starField;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class Control implements KeyListener, MouseMotionListener {
	
	private boolean up, down;
	private Point mousePoint;
	
	Control() {
		mousePoint = new Point();
	}
	
	boolean up() {
		return up;
	}
	
	boolean down() {
		return down;
	}
	
	Point mousePoint() {
		return mousePoint;
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		int keyCode = evt.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent evt) {
		int keyCode = evt.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent evt) {
	}
	
	@Override
	public void mouseMoved(MouseEvent evt) {
		Point newMousePoint = evt.getPoint();
		mousePoint.setLocation(
				(float) newMousePoint.x / Display.SCREEN_RESOLUTION.width * Display.SCREEN_WIDTH,
				(float) newMousePoint.y / Display.SCREEN_RESOLUTION.height * Display.SCREEN_HEIGHT);
	}
	
	@Override
	public void mouseDragged(MouseEvent evt) {
		mouseMoved(evt);
	}
}
