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

	public static Deque<undoStack> States = new ArrayDeque<undoStack>();

	public static int cur_point = 0;

	public static Player player = new Player();

	public static UndoableGame game = new UndoableGame();

	public void undo() {

		// int size = States.size();
		// undoStack check = States.peek();
		if (States.size() >= 1) {
			undoStack preState = new undoStack();
			preState = States.pop();
			int prePoint = preState.getPoints();
			Tile prePlayerTile = preState.getPlayerLocation();
			List<Tile> ghostTile = new ArrayList<Tile>();
			for (Tile ghost : preState.getGhostLocation()) {
				ghostTile.add(ghost);
			}

			// int a = cur_point;
			if (game.getPlayer().getPoints() > prePoint) {
				Food food = new Food();
				food.occupy(player.getTile());
				game.getPointManager().consumePointsOnBoard(player,
				        prePoint - game.getPlayer().getPoints());
			}

			player.deoccupy();
			player.occupy(prePlayerTile);

			for (Ghost ghost : game.getGhosts()) {
				ghost.deoccupy();
				ghost.occupy(ghostTile.remove(0));

			}
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
		currentState.pushStack(this);
		States.push(currentState);
		int size = States.size();
		super.movePlayer(dir);
		player = this.getPlayer();
		game = this;

	}

}
