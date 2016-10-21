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

	public UndoablePacman() {
		super();
		level = new Level();
	}

	public void undo() {
		this.withFactory(fact);

		this.withModelInteractor(undoPI);

		UndoableButtonPanel.withInteractor(undoPI);
		this.withButtonPanel(UndoableButtonPanel);

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