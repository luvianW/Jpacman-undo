package org.jpacman.undo;
import org.jpacman.framework.factory.*;
import org.jpacman.framework.model.Game;

public class DefaultUndoableGF extends DefaultGameFactory {
	
	private transient UndoableGame theUndoableGame;

	@Override
	public Game makeGame(){
		theUndoableGame = new UndoableGame();
		return theUndoableGame;
	}
	
	@Override
	protected Game getGame(){
		assert theUndoableGame != null;
		return theUndoableGame;
	}
}
