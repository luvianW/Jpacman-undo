package org.jpacman.undo;
import org.jpacman.framework.factory.*;
import org.jpacman.framework.model.Game;

public class DefaultUndoableGF extends DefaultGameFactory {

	@Override
	public Game makeGame(){
		return null;
	}
	@Override
	public Game getGame(){
		return makeGame();
	}
}
