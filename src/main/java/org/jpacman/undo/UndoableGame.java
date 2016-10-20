package org.jpacman.undo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

public class UndoableGame extends Game {

	Deque<undoStack> States = new ArrayDeque<undoStack>();

	public void undo() {
		undoStack preState = States.pop();
		int prePoint = preState.getPoints();
		Tile prePlayerTile = preState.getPlayerLocation();
		List<Tile> ghostTile = new ArrayList<Tile>();
		for (Tile ghost : preState.getGhostLocation()) {
			ghostTile.add(ghost);
		}

		Player player = this.getPlayer();
		if (player.getPoints() > prePoint) {
			Food food = new Food();
			food.occupy(player.getTile());
			this.getPointManager().consumePointsOnBoard(player, player.getPoints() - prePoint);
		}

		player.deoccupy();
		player.occupy(prePlayerTile);

		for (Ghost ghost : this.getGhosts()) {
			ghost.deoccupy();
			ghost.occupy(ghostTile.remove(0));

		}
	}

	@Override
	public void moveGhost(Ghost theGhost, Direction dir) {
		// push stack
		undoStack currentState = new undoStack();
		currentState.pushStack(this);
		States.push(currentState);
		super.moveGhost(theGhost, dir);
	}

	@Override
	public void movePlayer(Direction dir) {
		super.movePlayer(dir);
	}

}
