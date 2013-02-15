package edu.fmi.ggi.chaikin.model;

import java.awt.Point;
import java.util.List;

public class CurveLSystem extends LSystem {

	public CurveLSystem(String input) {
		super(input);
	}

	@Override
	protected void closeShape(List<Point> result) {
		// blank
	}

}
