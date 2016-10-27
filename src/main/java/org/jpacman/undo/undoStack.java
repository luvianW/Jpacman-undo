package org.jpacman.undo;

import java.util.ArrayList;
import java.util.List;

import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

class undoStack {
	// UndoableGame Game;
	Tile playerTile;
	int points;
	private List<Tile> ghostTile = new ArrayList<Tile>();

	/**
	 * Create a new undoStack node
	 */
	public undoStack() {
		playerTile = null;
		points = 0;
	}

	/**
	 * Store some variables(location, points) of the current game
	 * @param Game
	 */
	public void store(UndoableGame Game) {
		points = Game.getPlayer().getPoints();
		playerTile = Game.getPlayer().getTile();
		for (Ghost ghost : Game.getGhosts()) {
			ghostTile.add(ghost.getTile());
		}

	}

	/**
	 * set the player tile back to the previous stage
	 * @param game
	 */
	public void setBackPlayerTile(Game game) {
		game.getPlayer().deoccupy();
		game.getPlayer().occupy(playerTile);
	}

	/**
	 * set the points back
	 * @param game
	 */
	public void setBackPoints(Game game) {
		Player thePlayer = game.getPlayer();
		if (thePlayer.getPoints() > points) {
			Food food = new Food();
			food.occupy(thePlayer.getTile());
			game.getPointManager().consumePointsOnBoard(thePlayer,
			        points - thePlayer.getPoints());
		}
	}
/**
 * set the ghost tile back
 * @param game
 */
	public void setBackGhostTile(Game game) {
		for (Ghost ghost : game.getGhosts()) {
			ghost.deoccupy();
			ghost.occupy(ghostTile.remove(0));
		}
	}
}
