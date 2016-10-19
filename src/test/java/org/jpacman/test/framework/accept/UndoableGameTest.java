package org.jpacman.test.framework.accept;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Game;
import org.jpacman.test.framework.model.*;

public class UndoableGameTest extends GameTest{

	@Override
	public Game makePlay(String singleRow) throws FactoryException{
		return super.makePlay(singleRow);
		
	}
}
