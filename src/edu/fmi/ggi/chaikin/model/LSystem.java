package edu.fmi.ggi.chaikin.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LSystem {

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

	static {
		PATTERN_L_SYSTEM = Pattern.compile(PATTERN_REPLACE_POINT);
	}

	private final String input;

	private final StringBuilder builder;

	public static LSystem from(final String input, final boolean isClosed) {
		return isClosed ? new PolygonLSystem(input) : new CurveLSystem(input);
	}

	/**
	 * Construct a new L-system that will parse the <tt>input</tt> string using
	 * its internal rules
	 * 
	 * @param input
	 *            the input string to be parsed, using the rules of the l-system
	 */
	protected LSystem(final String input) {
		this.input = input;
		builder = new StringBuilder();
	}

	/**
	 * Computes the coordinates of the points that are created as a result of
	 * applying the rules of the L-system to the given input string
	 * 
	 * @return the points that are created as a result of the application of the
	 *         rules of the L-system on the given input string
	 */
	public Collection<Point> expand() {
		return smoothenEdges(input);
	}

	protected abstract void closeShape(final List<Point> result);

	private Collection<Point> smoothenEdges(final String vertexes) {
		final Matcher matcher = PATTERN_L_SYSTEM.matcher(vertexes);

		while (matcher.find()) {
			String[] splitGroup = matcher.group().split(PATTERN_SPLIT_POINT);

			final Point start = getStartPoint(splitGroup);
			final Point middle = getMiddlePoint(splitGroup);
			final Point end = getEndPoint(splitGroup);

			final Point newStartPoint = getNewStartPoint(start, middle);
			final Point newEndPoint = getNewEndPoint(middle, end);

			appendPoint(newStartPoint);
			appendPoint(newEndPoint);

			matcher.region(getMatcherStart(matcher), getMatcherEnd(matcher));
		}

		return parseAndAddPoints();
	}

	private void appendPoint(final Point point) {
		builder.append(point.x);
		builder.append(" ");
		builder.append(point.y);
		builder.append(" ");
	}

	private Collection<Point> parseAndAddPoints() {
		final List<Point> result = new ArrayList<Point>();
		parsePoints(result);
		closeShape(result);
		return result;
	}

	private void parsePoints(final List<Point> result) {
		final String points = builder.toString();
		final String[] stringPoints = points.split(PATTERN_SPLIT_POINT);

		for (int i = 0; i < stringPoints.length - 1; i += 2) {
			final int x = Integer.parseInt(stringPoints[i]);
			final int y = Integer.parseInt(stringPoints[i + 1]);
			result.add(new Point(x, y));
		}
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
}
