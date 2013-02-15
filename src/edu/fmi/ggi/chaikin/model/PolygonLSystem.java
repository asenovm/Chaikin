package edu.fmi.ggi.chaikin.model;

import java.awt.Point;
import java.util.List;

public class PolygonLSystem extends LSystem {

	public PolygonLSystem(String input) {
		super(input);
	}

	protected void closeShape(final List<Point> result) {
		final Point first = result.get(0);
		result.add(first);
	}

}
