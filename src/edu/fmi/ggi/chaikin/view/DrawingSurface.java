package edu.fmi.ggi.chaikin.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;

import edu.fmi.ggi.chaikin.listeners.DrawingCallback;
import edu.fmi.ggi.chaikin.listeners.DrawingObserver;
import edu.fmi.ggi.chaikin.model.Point;

public class DrawingSurface extends JFrame implements DrawingObserver,
		DrawingCallback {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -7055198570822987608L;

	private final DrawingPanel panel;

	private final DrawingCallback callback;

	public DrawingSurface(final DrawingCallback callback) {
		setLayout(new BorderLayout(0, 5));

		this.callback = callback;

		panel = new DrawingPanel();

		final Container container = getContentPane();
		container.add(panel, BorderLayout.PAGE_START);
		container.add(new ButtonPanel(this), BorderLayout.PAGE_END);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void onModelChanged(final List<Point> points) {
		panel.draw(points);
	}

	@Override
	public synchronized void addMouseListener(final MouseListener listener) {
		panel.addMouseListener(listener);
	}

	@Override
	public void onClosePolygonRequired() {
		callback.onClosePolygonRequired();
	}

	@Override
	public void onSmoothPolygonRequired() {
		callback.onSmoothPolygonRequired();
	}

}
