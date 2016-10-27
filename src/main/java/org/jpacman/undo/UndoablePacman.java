package org.jpacman.undo;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Level;
import org.jpacman.framework.ui.MainUI;

public class UndoablePacman extends MainUI {

	static final long serialVersionUID = -59470379321937183L;

	private final Level level;

	private transient undoPacInteraction undoPI = new undoPacInteraction();

	private UndoButton UndoableButtonPanel = new UndoButton();
	private DefaultUndoableGF fact = new DefaultUndoableGF();

	/**
	 * Create a new UndoGame UI for the default board. set the interact and factory with the model.
	 */
	public UndoablePacman() {
		super();
		level = new Level();
		this.withFactory(fact);
		this.withModelInteractor(undoPI);
	}

	/**
	 * set the interact with the undo button, and also set the undo button with the model.
	 */
	public void undo() {
		UndoableButtonPanel.withInteractor(undoPI);
		this.withButtonPanel(UndoableButtonPanel);
	}

	public UndoButton getUndoButton() {
		return UndoableButtonPanel;
	}

	@Override
	public void main() throws FactoryException {
		this.undo();
		super.main();
	}

	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}
}