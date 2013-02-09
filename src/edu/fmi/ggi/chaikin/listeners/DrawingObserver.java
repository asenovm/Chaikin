package edu.fmi.ggi.chaikin.listeners;

import java.util.List;

import edu.fmi.ggi.chaikin.model.Point;

public interface DrawingObserver {
	void onModelChanged(final List<Point> points);
}
