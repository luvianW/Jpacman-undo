package org.jpacman.test.framework.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.undo.UndoablePacman;
import org.junit.Test;

public class UndoStoryTest extends MovePlayerStoryTest {

	UndoablePacman newUndoPac;

	public Player p = new Player();

	// @Override
	// protected undoPacInteraction getEngine() {
	// return (undoPacInteraction) super.getEngine();
	// }

	@Override
	public MainUI makeUI() {
		newUndoPac = new UndoablePacman();
		return this.newUndoPac;
	}

	@Override
	public MainUI getUI() {
		return this.newUndoPac;

	}

	// pacman just move a empty cell
	// return to the previous cell.
	@Test
	public void undo_scenarios_71_tests() {

		// given
		getEngine().start();

		Tile playerTile = tileAt(1, 1);
		assertEquals(playerTile, getPlayer().getTile());
		// and

		Tile pre_tile = p.getTile();
		getEngine().up();

		// when
		newUndoPac.undo();

		assertEquals(p.getTile(), pre_tile);

	}

	// pacman just move a cell containing food
	// pacman move to the previous cell
	// and the food appear again on this cell, lose a point
	@Test
	public void undo_scenarios_72_tests() {

		// given

		Tile playerTile = tileAt(1, 1);
		// Tile aTile = tileAt(0, 0);
		// Tile bTile = tileAt(1, 1);
		// assertEquals(aTile, bTile);

		// assertEquals(playerTile, getPlayer().getTile());

		getEngine().start();

		Tile pre_tile = p.getTile();
		int pre_points = p.getPoints();

		// and
		getEngine().left();
		Tile left_tile = tileAt(0, 1);

		// when
		newUndoPac.undo();

		assertEquals(p.getTile(), pre_tile);
		assertEquals(p.getPoints(), pre_points);

	}

	// pacman is eaten by a ghost and game is terminated,
	// the game restarts and pacman returns to the cell before it dies.
	@Test
	public void undo_scenarios_73_tests() {

		// given

		Tile playerTile = tileAt(1, 1);
		// assertEquals(playerTile, getPlayer().getTile());

		getEngine().start();

		Tile pre_tile = p.getTile();
		Tile pre_ghost = theGhost().getTile();

		// and
		getEngine().right();

		// when
		newUndoPac.undo();

		assertTrue(p.isAlive());
		assertEquals(p.getTile(), pre_tile);
		assertEquals(theGhost().getTile(), pre_ghost);
	}

	// pacman is just failed to move towards a cell containing a wall;
	// pacman stays wherever it is.
	@Test
	public void undo_scenarios_74_tests() {

		// given
		getEngine().start();

		// and
		getEngine().up();
		Tile pre_tile = getPlayer().getTile();

		// when
		newUndoPac.undo();

		assertEquals(getPlayer().getTile(), pre_tile);

	}

	// a ghost is on a food cell;
	// the ghost move back to the previous cell, and the food cell is visible again.
	@Test
	public void undo_scenarios_75_tests() {

		// given
		getEngine().start();

		Tile pre_ghost = theGhost().getTile();
		// and
		getEngine().up();
		// when
		newUndoPac.undo();

		assertEquals(theGhost().getTile(), pre_ghost);
	}

	// a ghost is on an empty cell;
	// the ghost move back to the previous cell.
	@Test
	public void undo_scenarios_76_tests() {

		// given
		getEngine().start();

		Tile pre_ghost = theGhost().getTile();
		// and
		getEngine().up();
		// when
		newUndoPac.undo();

		assertEquals(theGhost().getTile(), pre_ghost);
	}

}
