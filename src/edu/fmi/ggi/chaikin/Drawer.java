package edu.fmi.ggi.chaikin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Drawer {

	private final DrawingPolygon polygon;

	private final DrawingSurface surface;

	private class DrawingSurfaceListener extends MouseAdapter {
		@Override
		public void mouseClicked(final MouseEvent event) {
			polygon.addPoint(event.getX(), event.getY());
		}
	}

	public Drawer() {
		polygon = new DrawingPolygon();
		surface = new DrawingSurface();

		polygon.addObserver(surface);
		surface.addMouseListener(new DrawingSurfaceListener());
	}

	public static void main(String[] args) {
		new Drawer();
	}
}
