package org.jpacman.test.framework.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.undo.UndoablePacman;
import org.jpacman.undo.undoPacInteraction;
import org.junit.Test;

public class UndoStoryTest extends MovePlayerStoryTest {

	UndoablePacman newUndoPac;

	@Override
	protected undoPacInteraction getEngine() {
		return (undoPacInteraction) super.getEngine();
	}

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
		Tile emptyTile = tileAt(1, 0);
		// given
		getEngine().start();
		// and

		Tile pre_tile = getPlayer().getTile();
		getEngine().up();

		// when
		newUndoPac.undo();

		assertEquals(getPlayer().getTile(), pre_tile);

	}

	// pacman just move a cell containing food
	// pacman move to the previous cell
	// and the food appear again on this cell, lose a point
	@Test
	public void undo_scenarios_72_tests() {

		// given
		getEngine().start();

		Tile pre_tile = getPlayer().getTile();
		int pre_points = getPlayer().getPoints();

		// and
		getEngine().left();

		// when
		newUndoPac.undo();

		assertEquals(getPlayer().getTile(), pre_tile);
		assertEquals(getPlayer().getPoints(), pre_points);

	}

	// pacman is eaten by a ghost and game is terminated,
	// the game restarts and pacman returns to the cell before it dies.
	@Test
	public void undo_scenarios_73_tests() {

		// given
		getEngine().start();

		Tile pre_tile = getPlayer().getTile();
		Tile pre_ghost = theGhost().getTile();

		// and
		getEngine().right();

		// when
		newUndoPac.undo();

		assertTrue(getPlayer().isAlive());
		assertEquals(getPlayer().getTile(), pre_tile);
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
