package edu.fmi.ggi.chaikin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	/**
	 * {@value}
	 */
	private static final int PANEL_HEIGHT = 500;

	/**
	 * {@value}
	 */
	private static final int PANEL_WIDTH = 500;

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -3936453562176627798L;

	public DrawingPanel() {
		this(null);
	}

	public DrawingPanel(boolean isDoubleBuffered) {
		this(null, isDoubleBuffered);
	}

	public DrawingPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		final Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);

		setBorder(null);

		setBackground(Color.decode("#E3E3E3"));
	}

	public DrawingPanel(LayoutManager layout) {
		this(layout, false);
	}

	public void draw(final List<Point> points) {
		final Graphics graphics = getGraphics();
		System.out.println("first point is " + points.get(0));
		System.out.println("last point is " + points.get(points.size() - 1));
		for (int i = 0; i < points.size() - 1; ++i) {
			final Point startPoint = points.get(i);
			final Point endPoint = points.get(i + 1);
			graphics.drawLine(startPoint.x, startPoint.y, endPoint.x,
					endPoint.y);
		}
	}
}
