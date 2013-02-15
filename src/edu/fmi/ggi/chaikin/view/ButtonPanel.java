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
	private static final long serialVersionUID = -6011518807905264738L;

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
	private static final int WIDTH_BUTTON = 100;

	/**
	 * {@value}
	 */
	private static final String TEXT_BUTTON_RESET = "Reset";

	/**
	 * {@value}
	 */
	private static final String TEXT_BUTTON_CLEAR_SCREEN = "Clear";

	/**
	 * {@value}
	 */
	private static final String TEXT_BUTTON_CLOSE_POLYGON = "Close";

	/**
	 * {@value}
	 */
	private static final String TEXT_BUTTON_SMOOTH_POLYGON = "Smoothen";

	/**
	 * {@value}
	 */
	private static final String BACKGROUND_BUTTON = "#A1998E";

	/**
	 * {@value}
	 */
	private static final String BACKGROUND_BUTTON_LAYOUT = "#AFB3B6";

	private abstract class BaseActionListener implements ActionListener {

		protected final DrawingCallback callback;

		protected BaseActionListener(final DrawingCallback callback) {
			this.callback = callback;
		}
	}

	private class CloseActionListener extends BaseActionListener {

		protected CloseActionListener(DrawingCallback callback) {
			super(callback);
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			callback.onClosePolygonRequired();
		}
	}

	private class SmoothActionListener extends BaseActionListener {

		protected SmoothActionListener(DrawingCallback callback) {
			super(callback);
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			callback.onSmoothPolygonRequired();
		}
	}

	private class ClearActionListener extends BaseActionListener {

		public ClearActionListener(DrawingCallback callback) {
			super(callback);
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			callback.onClearScreenRequired();
		}
	}

	private class ResetActionListener extends BaseActionListener {

		public ResetActionListener(DrawingCallback callback) {
			super(callback);
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			callback.onResetRequired();
		}
	}

	/**
	 * Constructs a new panel for holding the drawing-related buttons that will
	 * call the respective methods of the {@link DrawingCallback} given when
	 * needeadd
	 * 
	 * @param callback
	 *            the callback that is to be called when the respective events
	 *            in the {@link ButtonPanel} occur
	 */
	public ButtonPanel(final DrawingCallback callback) {
		super(new FlowLayout(), false);
		setBackground(Color.decode(BACKGROUND_BUTTON_LAYOUT));
		setPreferredSize(new Dimension(WIDTH_CONTAINER, HEIGHT_CONTAINER));

		addClosePolygonButton(callback);
		addSmoothPolygonButton(callback);
		addClearScreenButton(callback);
		addResetButton(callback);
	}

	private void addResetButton(final DrawingCallback callback) {
		final Button resetButton = getButton(TEXT_BUTTON_RESET);
		resetButton.addActionListener(new ResetActionListener(callback));
		add(resetButton);
	}

	private void addClearScreenButton(final DrawingCallback callback) {
		final Button clearScreenButton = getButton(TEXT_BUTTON_CLEAR_SCREEN);
		clearScreenButton.addActionListener(new ClearActionListener(callback));
		add(clearScreenButton);
	}

	private void addSmoothPolygonButton(final DrawingCallback callback) {
		final Button smoothenButton = getButton(TEXT_BUTTON_SMOOTH_POLYGON);
		smoothenButton.addActionListener(new SmoothActionListener(callback));
		add(smoothenButton);
	}

	private void addClosePolygonButton(final DrawingCallback callback) {
		final Button closeButton = getButton(TEXT_BUTTON_CLOSE_POLYGON);
		closeButton.addActionListener(new CloseActionListener(callback));
		add(closeButton);
	}

	private Button getButton(final String title) {
		final Button button = new Button(title);
		button.setPreferredSize(new Dimension(WIDTH_BUTTON, HEIGHT_BUTTON));
		button.setBackground(Color.decode(BACKGROUND_BUTTON));
		button.setFocusable(false);
		return button;
	}

}
