package edu.fmi.ggi.chaikin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -6011518807905264738L;

	public ButtonPanel() {
		this(null);
	}

	public ButtonPanel(boolean isDoubleBuffered) {
		this(null, isDoubleBuffered);
	}

	public ButtonPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		setBackground(Color.decode("#AFB3B6"));

		final Dimension dimension = new Dimension(200, 100);
		setPreferredSize(dimension);
	}

	public ButtonPanel(LayoutManager layout) {
		this(layout, false);
	}

}
