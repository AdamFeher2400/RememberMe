package com.swing.rememberme.engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EngineTest {

	@Test
	void testExistUser() {
		assertTrue(GameEngine.getInstance().ExistUser("123", "123"));
		assertFalse(GameEngine.getInstance().ExistUser("123", "1234"));
		assertFalse(GameEngine.getInstance().ExistUser("1234", "1234"));
	}

	@Test
	void testExistName() {
		assertTrue(GameEngine.getInstance().ExistName("123"));
		assertFalse(GameEngine.getInstance().ExistName("124"));
	}

	@Test
	void testStartGame() {
		GameEngine.getInstance().setLevel("Easy");
		GameEngine.getInstance().setCategory("Math");
		GameEngine.getInstance().startGame();
		assertTrue(GameEngine.getInstance().isPlaying());
	}

	@Test
	void testEndGame() {
		GameEngine.getInstance().endGame();
		assertFalse(GameEngine.getInstance().isPlaying());
	}

}
