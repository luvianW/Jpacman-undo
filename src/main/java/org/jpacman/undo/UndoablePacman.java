package org.jpacman.undo;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.MainUI;

public class UndoablePacman extends MainUI {

	static final long serialVersionUID = -59470379321937183L;

	private transient undoPacInteraction undoPI;

	private UndoButton UndoableButtonPanel;
	private DefaultUndoableGF fact;

	public void undo() {
		this.withModelInteractor(undoPI);
		this.withButtonPanel(UndoableButtonPanel);
		this.withFactory(fact);
	}

	@Override
	public void main() throws FactoryException {
		initialize();
		undo();
		start();
	}

	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}
}