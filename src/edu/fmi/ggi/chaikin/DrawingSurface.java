package edu.fmi.ggi.chaikin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;

public class DrawingSurface extends JFrame implements DrawingObserver {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -7055198570822987608L;

	private final DrawingPanel panel;

	public DrawingSurface() {
		this("");
	}

	public DrawingSurface(GraphicsConfiguration gc) {
		this("", gc);
	}

	public DrawingSurface(String title, GraphicsConfiguration gc) {
		super(title, gc);
		setLayout(new BorderLayout(0, 5));

		panel = new DrawingPanel();

		final Container container = getContentPane();
		container.add(panel, BorderLayout.PAGE_START);
		container.add(new ButtonPanel(), BorderLayout.PAGE_END);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public DrawingSurface(String title) throws HeadlessException {
		this(title, null);
	}

	@Override
	public void onModelChanged(final List<Point> points) {
		panel.draw(points);
	}

	@Override
	public synchronized void addMouseListener(final MouseListener listener) {
		panel.addMouseListener(listener);
	}

}
