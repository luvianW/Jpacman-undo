package org.jpacman.test.framework.accept;

import org.jpacman.framework.ui.MainUI;
import org.jpacman.undo.UndoablePacman;
import org.junit.Test;

public class UndoStoryTest extends MovePlayerStoryTest {

	UndoablePacman newUndoPac;

	@Override
	public MainUI makeUI() {
		newUndoPac = new UndoablePacman();
		return newUndoPac;
	}

	@Override
	public MainUI getUI() {
		return newUndoPac;

	}

	@Test
	public void undo_scenarios_71_tests() {

	}

	@Test
	public void undo_scenarios_72_tests() {

	}

	@Test
	public void undo_scenarios_73_tests() {

	}

	@Test
	public void undo_scenarios_74_tests() {

	}

	@Test
	public void undo_scenarios_75_tests() {

	}

	@Test
	public void undo_scenarios_76_tests() {

	}

}
