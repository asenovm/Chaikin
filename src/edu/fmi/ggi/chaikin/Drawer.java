package edu.fmi.ggi.chaikin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.fmi.ggi.chaikin.listeners.DrawingCallback;
import edu.fmi.ggi.chaikin.model.Polygon;
import edu.fmi.ggi.chaikin.view.DrawingFrame;

public class Drawer implements DrawingCallback {

	private final Polygon polygon;

	private final DrawingFrame surface;

	private class DrawingSurfaceListener extends MouseAdapter {
		@Override
		public void mouseClicked(final MouseEvent event) {
			polygon.addPoint(event.getX(), event.getY());
		}
	}

	public Drawer() {
		polygon = new Polygon();
		surface = new DrawingFrame(this);

		polygon.addObserver(surface);
		surface.addMouseListener(new DrawingSurfaceListener());
	}

	public static void main(String[] args) {
		new Drawer();
	}

	@Override
	public void onClosePolygonRequired() {
		polygon.close();
	}

	@Override
	public void onSmoothPolygonRequired() {
		polygon.smoothenPolygon();
	}
}
