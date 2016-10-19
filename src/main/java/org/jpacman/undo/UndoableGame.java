package org.jpacman.undo;
import org.jpacman.framework.model.*;

public class UndoableGame extends Game {
	public void undo(){
		
	}
	
	@Override
	public void moveGhost(Ghost theGhost, Direction dir){
		super.moveGhost(theGhost, dir);
	}
	
	@Override
	public void movePlayer(Direction dir){
		super.movePlayer(dir);
	}

}
