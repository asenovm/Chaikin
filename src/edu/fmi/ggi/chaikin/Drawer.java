package edu.fmi.ggi.chaikin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.fmi.ggi.chaikin.listeners.DrawingCallback;
import edu.fmi.ggi.chaikin.model.Curve;
import edu.fmi.ggi.chaikin.view.DrawingFrame;

public class Drawer implements DrawingCallback {

	private final Curve polygon;

	private final DrawingFrame frame;

	private class DrawingSurfaceListener extends MouseAdapter {
		@Override
		public void mouseClicked(final MouseEvent event) {
			polygon.addPoint(event.getPoint());
		}
	}

	public Drawer() {
		polygon = new Curve();
		frame = new DrawingFrame(this);

		polygon.addObserver(frame);
		frame.addMouseListener(new DrawingSurfaceListener());
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

	@Override
	public void onClearScreenRequired() {
		// blank
	}

	@Override
	public void onResetRequired() {
		polygon.reset();
	}

}
