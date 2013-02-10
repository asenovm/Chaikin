package edu.fmi.ggi.chaikin.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.fmi.ggi.chaikin.listeners.DrawingCallback;

public class ButtonPanel extends JPanel {

	/**
	 * {@value}
	 */
	private static final int HEIGHT_CONTAINER = 100;

	/**
	 * {@value}
	 */
	private static final int WIDTH_CONTAINER = 500;

	/**
	 * {@value}
	 */
	private static final int HEIGHT_BUTTON = 60;

	/**
	 * {@value}
	 * 
	 */
	private static final int WIDTH_BUTTON = 150;

	/**
	 * {@value}
	 */
	private static final String TEXT_BUTTON_CLOSE_POLYGON = "Close Polygon";

	/**
	 * {@value}
	 */
	private static final String TEXT_BUTTON_SMOOTH_POLYGON = "Smooth Polygon";

	/**
	 * {@value}
	 */
	private static final String BACKGROUND_BUTTON = "#A1998E";

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = -6011518807905264738L;

	/**
	 * {@value}
	 */
	private static final String BACKGROUND_BUTTON_LAYOUT = "#AFB3B6";

	public ButtonPanel(final DrawingCallback callback) {
		super(new FlowLayout(), false);
		setBackground(Color.decode(BACKGROUND_BUTTON_LAYOUT));
		setPreferredSize(new Dimension(WIDTH_CONTAINER, HEIGHT_CONTAINER));

		final Button closePolygonButton = getButton(TEXT_BUTTON_CLOSE_POLYGON);
		closePolygonButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				callback.onClosePolygonRequired();
			}
		});
		add(closePolygonButton);

		final Button smoothPolygonButton = getButton(TEXT_BUTTON_SMOOTH_POLYGON);
		smoothPolygonButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				callback.onSmoothPolygonRequired();
			}
		});
		add(smoothPolygonButton);
	}

	private Button getButton(final String title) {
		final Button button = new Button(title);
		button.setPreferredSize(new Dimension(WIDTH_BUTTON, HEIGHT_BUTTON));
		button.setBackground(Color.decode(BACKGROUND_BUTTON));
		button.setFocusable(false);
		return button;
	}

}