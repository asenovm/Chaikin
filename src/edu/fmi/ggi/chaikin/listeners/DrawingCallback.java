package edu.fmi.ggi.chaikin.listeners;

/**
 * Implementations of this interface are listeners for events, related to
 * polygon operations
 * 
 * @author martin
 * 
 */
public interface DrawingCallback {
	/**
	 * A callback fired when closing of the polygon has been required
	 */
	void onClosePolygonRequired();

	/**
	 * A callback fired when smoothing of the edges of the polygon has been
	 * required
	 */
	void onSmoothPolygonRequired();

	/**
	 * A callback fired when the button for clearing the current drawing on the
	 * screen has been clicked
	 */
	void onClearScreenRequired();
}
