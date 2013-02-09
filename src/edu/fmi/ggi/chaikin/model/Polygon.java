package edu.fmi.ggi.chaikin.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.fmi.ggi.chaikin.listeners.DrawingObserver;

public class Polygon {

	private final Set<DrawingObserver> observers;

	private final List<Point> points;

	public Polygon() {
		observers = new LinkedHashSet<DrawingObserver>();
		points = new LinkedList<Point>();
	}

	public void addPoint(final int x, final int y) {
		points.add(new Point(x, y));
		notifyObservers();
	}

	private void notifyObservers() {
		for (final DrawingObserver observer : observers) {
			observer.onModelChanged(Collections.unmodifiableList(points));
		}
	}

	public boolean addObserver(final DrawingObserver observer) {
		return observers.add(observer);
	}

	public boolean removeObserver(final DrawingObserver observer) {
		return observers.remove(observer);
	}

	public void close() {
		final Point first = points.get(0);
		points.add(first);
		notifyObservers();
	}

}
