package org.jpacman.undo;
import org.jpacman.framework.factory.*;
import org.jpacman.framework.model.Game;

public class DefaultUndoableGF extends DefaultGameFactory {
private transient UndoableGame theGame;
	@Override
	public Game makeGame(){
		theGame = new UndoableGame();
		return theGame;
	}
	@Override
	public Game getGame(){
		assert theGame != null;
		return theGame;
	}
}
