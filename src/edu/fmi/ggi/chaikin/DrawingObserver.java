package edu.fmi.ggi.chaikin;

import java.util.List;

public interface DrawingObserver {
	void onModelChanged(final List<Point> points);
}
