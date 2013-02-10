package edu.fmi.ggi.chaikin.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (final Point point : points) {
			builder.append(point);
			builder.append(" ");
		}
		builder.append(points.get(1));
		return builder.toString();
	}

	public void smoothEdges() {
		final String vertexes = toString();
		System.out.println("*****vertexess*******");
		System.out.println(vertexes);
		System.out.println("*********************");
		points.clear();
		final Pattern pattern = Pattern
				.compile("(\\d+) (\\d+) (\\d+) (\\d+) (\\d+) (\\d+)");
		final Matcher matcher = pattern.matcher(vertexes);
		while (matcher.find()) {
			System.out.println("current group is " + matcher.group());
			String[] splitGroup = matcher.group().split(" ");
			final Point start = new Point(Integer.parseInt(splitGroup[0]),
					Integer.parseInt(splitGroup[1]));
			final Point middle = new Point(Integer.parseInt(splitGroup[2]),
					Integer.parseInt(splitGroup[3]));
			final Point end = new Point(Integer.parseInt(splitGroup[4]),
					Integer.parseInt(splitGroup[5]));
			final Point firstNewPoint = new Point((int) Math.round(0.25
					* start.x + 0.75 * middle.x), (int) Math.round(0.25
					* start.y + 0.75 * middle.y));
			final Point secondNewPoint = new Point((int) Math.round(0.75
					* middle.x + 0.25 * end.x), (int) Math.round(0.75
					* middle.y + 0.25 * end.y));

			points.add(firstNewPoint);
			points.add(secondNewPoint);
			matcher.region(matcher.regionStart() + matcher.group(1).length()
					+ matcher.group(2).length() + 2, matcher.regionEnd());
		}

		points.add(points.get(0));
		notifyObservers();
	}

}
