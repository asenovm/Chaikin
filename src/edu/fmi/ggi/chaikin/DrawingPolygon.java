package edu.fmi.ggi.chaikin;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DrawingPolygon {

	private final Set<DrawingObserver> observers;

	private final Set<Point> points;

	public DrawingPolygon() {
		observers = new LinkedHashSet<DrawingObserver>();
		points = new LinkedHashSet<Point>();
	}

	public void addPoint(final int x, final int y) {
		points.add(new Point(x, y));
		notifyObservers();
	}

	private void notifyObservers() {
		for (final DrawingObserver observer : observers) {
			observer.onModelChanged(Arrays.asList(points.toArray(new Point[0])));
		}
	}

	public boolean addObserver(final DrawingObserver observer) {
		return observers.add(observer);
	}

	public boolean removeObserver(final DrawingObserver observer) {
		return observers.remove(observer);
	}

}
