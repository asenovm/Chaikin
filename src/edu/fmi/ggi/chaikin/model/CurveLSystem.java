package edu.fmi.ggi.chaikin.model;

import java.awt.Point;
import java.util.List;

/**
 * An L-system that specifies the rules for drawing an open curve on the screen
 * 
 * @author martin
 * 
 */
public class CurveLSystem extends LSystem {

	/**
	 * Constructs a new L-system for dealing with curves that will operate on
	 * the given <tt>input</tt> string
	 * 
	 * @param input
	 *            the input string on which the L-system will operate
	 */
	public CurveLSystem(String input) {
		super(input);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void closeShape(List<Point> result) {
		// blank
	}

}
