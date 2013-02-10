package edu.fmi.ggi.chaikin.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;

import edu.fmi.ggi.chaikin.listeners.DrawingCallback;
import edu.fmi.ggi.chaikin.listeners.DrawingObserver;

/**
 * A view to hold the entire layout of the application
 * 
 * @author martin
 * 
 */
public class DrawingFrame extends JFrame implements DrawingObserver,
		DrawingCallback {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -7055198570822987608L;

	private final DrawingSurface panel;

	private final DrawingCallback callback;

	/**
	 * Constructs a new drawing frame that holds the entire application layout
	 * 
	 * @param callback
	 *            the callback that is to be fired when specific events occur in
	 *            the drawing frame
	 */
	public DrawingFrame(final DrawingCallback callback) {
		setLayout(new BorderLayout(0, 5));

		this.callback = callback;

		panel = new DrawingSurface();

		final Container container = getContentPane();
		container.add(panel, BorderLayout.PAGE_START);
		container.add(new ButtonPanel(this), BorderLayout.PAGE_END);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public void onModelChanged(final List<Point> points) {
		panel.draw(points);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void addMouseListener(final MouseListener listener) {
		panel.addMouseListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClosePolygonRequired() {
		callback.onClosePolygonRequired();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSmoothPolygonRequired() {
		callback.onSmoothPolygonRequired();
	}

}
