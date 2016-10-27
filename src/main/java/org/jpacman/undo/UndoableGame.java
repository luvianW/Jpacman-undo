package org.jpacman.undo;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;

public class UndoableGame extends Game {

	public static Deque<undoStack> States = new ArrayDeque<undoStack>();

	public static int cur_point = 0;

	public static Player player = new Player();

	public static UndoableGame game = new UndoableGame();

	/**
	 * return to the previous stage of the game
	 */
	public void undo() {

		// int size = States.size();
		// undoStack check = States.peek();
		if (States.size() >= 1) {
			undoStack preState = new undoStack();
			preState = States.pop();
			preState.setBackPoints(game);
			preState.setBackPlayerTile(game);
			preState.setBackGhostTile(game);

			if (!player.isAlive()) {
				player.resurrect();
			}

			notifyViewers();
		}
	}

	@Override
	public void moveGhost(Ghost theGhost, Direction dir) {
		super.moveGhost(theGhost, dir);
	}

	@Override
	public void movePlayer(Direction dir) {

		// push stack
		undoStack currentState = new undoStack();
		currentState.store(this);
		States.push(currentState);
		int size = States.size();
		super.movePlayer(dir);
		player = this.getPlayer();
		game = this;

	}

}
