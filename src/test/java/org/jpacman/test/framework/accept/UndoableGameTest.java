package org.jpacman.test.framework.accept;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Game;
import org.jpacman.test.framework.model.GameTest;
import org.jpacman.undo.UndoableGame;

public class UndoableGameTest extends GameTest {

	@Override
	public Game makePlay(String singleRow) throws FactoryException {
		// return super.makePlay(singleRow);
		MapParser p = new MapParser(makeFactory());
		Game theGame = p.parseMap(new String[] { singleRow });
		return (UndoableGame) theGame;
	}
}