package edu.fmi.ggi.chaikin.model;

import java.awt.Point;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.fmi.ggi.chaikin.listeners.DrawingObserver;

public class Curve {

	private final Set<DrawingObserver> observers;

	private final List<Point> points;

	/**
	 * Constructs a new empty polygon
	 */
	public Curve() {
		observers = new LinkedHashSet<DrawingObserver>();
		points = new LinkedList<Point>();
	}

	/**
	 * Adds the point with the specified <tt>x</tt> and <tt>y</tt> coordinates
	 * to this polygon.
	 * 
	 * @param x
	 *            the <tt>x</tt> coordinate of the point that is to be added
	 * @param y
	 *            the <tt>y</tt> coordinate of the point that is to be added
	 */
	public void addPoint(final Point point) {
		points.add(point);
		notifyObservers();
	}

	/**
	 * Adds the specified <tt>observer</tt> to the list of observers that is to
	 * be notified when the polygon changes
	 * 
	 * @param observer
	 *            the observer that is to be notified when the polygon changes
	 * @return whether or not the <tt>observer</tt> has been successfully added
	 *         to the notification list of the model
	 */
	public boolean addObserver(final DrawingObserver observer) {
		return observers.add(observer);
	}

	/**
	 * Removes the observer given from the notification list
	 * 
	 * @param observer
	 *            the observer that is to be removed from the notification list
	 * @return whether or not the <tt>observer</tt> given has been successfully
	 *         removed from the notification list
	 */
	public boolean removeObserver(final DrawingObserver observer) {
		return observers.remove(observer);
	}

	/**
	 * Closes the polygon. That is to connect the last point added with the
	 * initial point of the polygon
	 */
	public void close() {
		final Point first = points.get(0);
		points.add(first);
		notifyObservers();
	}

	/**
	 * Smoothens the edges of the polygon so that it forms a B-spline with the
	 * same control points as the vertexes of the polygon
	 */
	public void smoothenPolygon() {
		final String vertexes = this.toString();

		final Point start = points.get(0);
		final Point end = points.get(points.size() - 1);
		final boolean isClosed = isClosed();

		points.clear();

		if (!isClosed) {
			points.add(start);
		}

		points.addAll(LSystem.from(vertexes, isClosed).expand());

		if (!isClosed) {
			points.add(end);
		}

		notifyObservers();
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (final Point point : points) {
			builder.append(point.x);
			builder.append(" ");
			builder.append(point.y);
			builder.append(" ");
		}

		if (isClosed()) {
			final Point startPoint = points.get(1);
			builder.append(startPoint.x);
			builder.append(" ");
			builder.append(startPoint.y);
		}
		return builder.toString();
	}

	private void notifyObservers() {
		for (final DrawingObserver observer : observers) {
			observer.onModelChanged(Collections.unmodifiableList(points));
		}
	}

	public boolean isClosed() {
		final Point startPoint = points.get(0);
		final Point endPoint = points.get(points.size() - 1);
		return startPoint.equals(endPoint);
	}
}
