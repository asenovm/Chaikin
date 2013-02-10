package edu.fmi.ggi.chaikin.listeners;

import java.awt.Point;
import java.util.List;

/**
 * Implementations of this interface are listeners for changes in the drawing
 * model
 * 
 * @author martin
 * 
 */
public interface DrawingObserver {
	/**
	 * Callback fired when the drawing model has changed
	 * 
	 * @param points
	 *            the new set of points that the model consists of
	 */
	void onModelChanged(final List<Point> points);
}
