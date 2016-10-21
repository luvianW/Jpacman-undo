package org.jpacman.undo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;

public class UndoButton extends ButtonPanel {

	// what is this?
	private static final long serialVersionUID = -3960399402611547765L;
	/**
	 * 
	 */

	// not sure for here, change later
	// private JButton startButton;
	// private JButton stopButton;
	protected JButton undoButton;

	@Override
	public void initialize() {

		super.initialize();

		// startButton = new JButton("Start");
		// stopButton = new JButton("Stop");
		undoButton = new JButton("Undo");
		// initializeStartButton();
		// initializeStopButton();
		initializeUndoButton();

		// JButton exitButton = createExitButton();

		// setName("jpacman.buttonPanel");
		// addButton(startButton);
		// addButton(stopButton);
		// addButton(exitButton);
		addButton(undoButton);
	}

	protected void initializeUndoButton() {
		undoButton.setEnabled(true);
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getPacmanInteractor().undo();
				// if (getPacmanInteractor().getCurrentState() == MatchState.PLAYING)
				// undoButton.setEnabled(true);
			}
		});
		undoButton.setName("jpacman.undo");
	}

	public undoPacInteraction getPacmanInteractor() {
		return (undoPacInteraction) super.getPacmanInteractor();
	}

}
