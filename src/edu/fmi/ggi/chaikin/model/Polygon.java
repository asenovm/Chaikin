package edu.fmi.ggi.chaikin.model;

import java.awt.Point;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.fmi.ggi.chaikin.listeners.DrawingObserver;

public class Polygon {

	/**
	 * {@value}
	 */
	private static final String PATTERN_SPLIT_POINT = " ";

	/**
	 * {@value}
	 */
	private static final String PATTERN_REPLACE_POINT = "(\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (\\d+)";

	/**
	 * The pattern to be used for replacing the points in our polygon
	 */
	private static final Pattern PATTERN_L_SYSTEM;

	private final Set<DrawingObserver> observers;

	private final List<Point> points;

	static {
		PATTERN_L_SYSTEM = Pattern.compile(PATTERN_REPLACE_POINT);
	}

	/**
	 * Constructs a new empty polygon
	 */
	public Polygon() {
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
	public void addPoint(final int x, final int y) {
		points.add(new Point(x, y));
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

		points.clear();
		smoothenEdges(vertexes);

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

		final Point startPoint = points.get(1);
		builder.append(startPoint.x);
		builder.append(" ");
		builder.append(startPoint.y);
		return builder.toString();
	}

	private Point getNewEndPoint(final Point middle, final Point end) {
		return new Point((int) Math.round(0.75 * middle.x + 0.25 * end.x),
				(int) Math.round(0.75 * middle.y + 0.25 * end.y));
	}

	private Point getNewStartPoint(final Point start, final Point middle) {
		return new Point((int) Math.round(0.25 * start.x + 0.75 * middle.x),
				(int) Math.round(0.25 * start.y + 0.75 * middle.y));
	}

	private Point getEndPoint(String[] splitGroup) {
		return new Point(Integer.parseInt(splitGroup[4]),
				Integer.parseInt(splitGroup[5]));
	}

	private Point getMiddlePoint(String[] splitGroup) {
		return new Point(Integer.parseInt(splitGroup[2]),
				Integer.parseInt(splitGroup[3]));
	}

	private Point getStartPoint(String[] splitGroup) {
		return new Point(Integer.parseInt(splitGroup[0]),
				Integer.parseInt(splitGroup[1]));
	}

	private int getMatcherEnd(final Matcher matcher) {
		return matcher.regionEnd();
	}

	private int getMatcherStart(final Matcher matcher) {
		return matcher.regionStart() + matcher.group(1).length()
				+ matcher.group(2).length() + 2;
	}

	private void notifyObservers() {
		for (final DrawingObserver observer : observers) {
			observer.onModelChanged(Collections.unmodifiableList(points));
		}
	}

	private void smoothenEdges(final String vertexes) {
		final Matcher matcher = PATTERN_L_SYSTEM.matcher(vertexes);

		final StringBuilder builder = new StringBuilder();

		while (matcher.find()) {
			String[] splitGroup = matcher.group().split(PATTERN_SPLIT_POINT);

			final Point start = getStartPoint(splitGroup);
			final Point middle = getMiddlePoint(splitGroup);
			final Point end = getEndPoint(splitGroup);

			final Point newStartPoint = getNewStartPoint(start, middle);
			final Point newEndPoint = getNewEndPoint(middle, end);

			appendPoint(builder, newStartPoint);
			appendPoint(builder, newEndPoint);

			matcher.region(getMatcherStart(matcher), getMatcherEnd(matcher));
		}

		parseAndAddPoints(builder.toString());
	}

	private void appendPoint(final StringBuilder builder, final Point point) {
		builder.append(point.x);
		builder.append(" ");
		builder.append(point.y);
		builder.append(" ");
	}

	private void parseAndAddPoints(final String points) {
		final String[] stringPoints = points.split(" ");
		for (int i = 0; i < stringPoints.length - 1; i += 2) {
			final int x = Integer.parseInt(stringPoints[i]);
			final int y = Integer.parseInt(stringPoints[i + 1]);
			this.points.add(new Point(x, y));
		}

		this.points.add(this.points.get(0));
	}

}
