package edu.fmi.ggi.chaikin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.fmi.ggi.chaikin.listeners.DrawingCallback;
import edu.fmi.ggi.chaikin.model.Curve;
import edu.fmi.ggi.chaikin.view.DrawingFrame;

/**
 * The main class of the application, responsible for dispatching events to the
 * respective handlers
 * 
 * @author martin
 * 
 */
public class Drawer implements DrawingCallback {

	private final Curve curve;

	private final DrawingFrame frame;

	private class DrawingSurfaceListener extends MouseAdapter {
		@Override
		public void mouseClicked(final MouseEvent event) {
			curve.addPoint(event.getPoint());
		}
	}

	/**
	 * Constructs a new drawer instance to be used for drawing on a surface
	 */
	public Drawer() {
		curve = new Curve();
		frame = new DrawingFrame(this);

		curve.addObserver(frame);
		frame.addMouseListener(new DrawingSurfaceListener());
	}

	public static void main(String[] args) {
		new Drawer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClosePolygonRequired() {
		curve.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSmoothPolygonRequired() {
		curve.smoothenPolygon();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClearScreenRequired() {
		// blank
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onResetRequired() {
		curve.reset();
	}

}
