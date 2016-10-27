package org.jpacman.undo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;

public class UndoButton extends ButtonPanel {

	// what is this?
	private static final long serialVersionUID = -3960399402611547765L;

	private JButton undoButton;

	@Override
	public void initialize() {
		super.initialize();
		undoButton = new JButton("Undo");
		initializeUndoButton();
		addButton(undoButton);
	}

	/**
	 * Create the undo button.
	 */
	protected void initializeUndoButton() {
		undoButton.setEnabled(true);
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getPacmanInteractor().undo();
				start();
			}
		});
		undoButton.setName("jpacman.undo");
	}

	@Override
	public undoPacInteraction getPacmanInteractor() {
		return (undoPacInteraction) super.getPacmanInteractor();
	}

}
