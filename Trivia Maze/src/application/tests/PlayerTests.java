package application.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import application.Maze;
import application.Player;
import application.enums.Direction;

class PlayerTests
{

	@Test
	void playerConstructor_defaultValueOfPlayerXIsNegativeOne_TRUE()
	{
		Player sut = new Player();
		assertTrue(sut.getPlayerX() == -1);
	}

	@Test
	void playerConstructor_defaultValueOfPlayerYIsNegativeOne_TRUE()
	{
		Player sut = new Player();
		assertTrue(sut.getPlayerY() == -1);
	}

	@Test
	void setSpawn_playerXWithinMaze_TRUE()
	{
		Player sut = new Player();
		sut.setSpawn(5, 5);
		assertTrue(sut.getPlayerX() < 5 && sut.getPlayerX() >= 0);
	}

	@Test
	void setSpawn_playerYWithinMaze_TRUE()
	{
		Player sut = new Player();
		sut.setSpawn(5, 5);
		assertTrue(sut.getPlayerY() < 5 && sut.getPlayerY() >= 0);
	}

	@Test
	void movePlayer_returnsFlaseIfNotExit_FALSE()
	{
		Maze gameMaze = new Maze(5, 5);
		gameMaze.setExitX(2);
		gameMaze.setExitY(2);
		Player sut = new Player();
		sut.setPlayerX(2);
		sut.setPlayerY(2);
		assertFalse(sut.movePlayer(gameMaze, Direction.NORTH));
	}

	@Test
	void movePlayer_returnsTrueIfNotExit_TRUE()
	{
		Maze gameMaze = new Maze(5, 5);
		gameMaze.setExitX(2);
		gameMaze.setExitY(1);
		Player sut = new Player();
		sut.setPlayerX(2);
		sut.setPlayerY(2);
		assertTrue(sut.movePlayer(gameMaze, Direction.NORTH));
	}

	@Test
	void getPlayerX_returnsSetValue_TRUE()
	{
		Player sut = new Player();
		sut.setPlayerX(2);
		assertTrue(sut.getPlayerX() == 2);
	}

	@Test
	void getPlayerY_returnsSetValue_TRUE()
	{
		Player sut = new Player();
		sut.setPlayerY(2);
		assertTrue(sut.getPlayerY() == 2);
	}

	@Test
	void setPlayerX_throwsIllegalArgumentExceptionIfOutOfBoundsOfMaze_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Player sut = new Player();
			sut.setPlayerX(-1);
		});
		assertEquals("Passed X value is out of the bounds of the maze.", exception.getMessage());
	}

	@Test
	void setPlayerY_throwsIllegalArgumentExceptionIfOutOfBoundsOfMaze_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Player sut = new Player();
			sut.setPlayerY(-1);
		});
		assertEquals("Passed Y value is out of the bounds of the maze.", exception.getMessage());
	}

}
