package edu.fmi.ggi.chaikin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

/**
 * A layout to represent the box in which the user can draw the form he'd like
 * to be smoothen
 * 
 * @author martin
 * 
 */
public class DrawingSurface extends JPanel {

	/**
	 * {@value}
	 */
	private static final String BACKGROUND_COLOR_DRAWING_PANEL = "#E3E3E3";

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

	/**
	 * Constructs a new drawing panel in which the user drawings are being drawn
	 */
	public DrawingSurface() {
		final Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);

		setBackground(Color.decode(BACKGROUND_COLOR_DRAWING_PANEL));
	}

	/**
	 * Draws a polygon out of the respective list of points that represents its
	 * edges
	 * 
	 * @param points
	 *            the list of points that represents the edges of the polygon
	 *            that is to be drawn
	 */
	public void draw(final List<Point> points) {
		final Graphics graphics = getGraphics();
		for (int i = 0; i < points.size() - 1; ++i) {
			final Point startPoint = points.get(i);
			final Point endPoint = points.get(i + 1);
			graphics.drawLine(startPoint.x, startPoint.y, endPoint.x,
					endPoint.y);
		}
	}
}
