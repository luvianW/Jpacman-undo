package org.jpacman.undo;

import org.jpacman.framework.ui.PacmanInteraction;

public class undoPacInteraction extends PacmanInteraction {

	private UndoableGame undoableInteractor = new UndoableGame();

	public void undo() {
		undoableInteractor.undo();
		updateState();
	}

}
