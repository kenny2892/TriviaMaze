package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMazeTester {
	
	private static int mazeRows;
	private static int mazeColumns;
	private static Room[][] gameMaze;

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		mazeRows = 5;
		mazeColumns = 5;
		Maze.createMaze(mazeRows, mazeColumns);
		gameMaze = Maze.gameMaze;
		Maze.setEnteranceExit(mazeRows, mazeColumns);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
		
	}

	@BeforeEach
	void setUp() throws Exception
	{
		
	}

	@AfterEach
	void tearDown() throws Exception
	{
		
	}

	@Test
	void createMaze_correctColumnLength_5()
	{
		assertEquals(5, gameMaze.length);
	}
	
	@Test
	void createMaze_corretRowLength_5()
	{
		assertEquals(5, gameMaze[0].length);
	}
	
	@Test
	void createMaze_eachElementIsARoom_True()
	{
		boolean eachElementIsARoom = true;
		for(int rowIndex = 0; rowIndex < gameMaze[0].length; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < gameMaze.length; columnIndex++)
			{
				if(!(gameMaze[rowIndex][columnIndex] instanceof Room))
				{
					eachElementIsARoom = false;
					assertTrue(eachElementIsARoom);
				}
			}
		}
		assertTrue(eachElementIsARoom);
	}
	
	@Test
	void createMaze_eachRoomHasFourDoors_True()
	{
		boolean eachRoomHasFourDoors = true;
		for(int rowIndex = 0; rowIndex < gameMaze[0].length; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < gameMaze.length; columnIndex++)
			{
				
				int northDoorIndex = rowIndex - 1;
				int southDoorIndex = rowIndex + 1;
				int eastDoorIndex = columnIndex + 1;
				int westDoorIndex = columnIndex - 1;
				
				Room currentRoom = gameMaze[rowIndex][columnIndex];
				
				if
				(
					!(currentRoom.isDoorLocked(Direction.NORTH) == northDoorIndex < 0) ||
					!(currentRoom.isDoorLocked(Direction.SOUTH) == southDoorIndex >= gameMaze[0].length) ||
					!(currentRoom.isDoorLocked(Direction.EAST) == eastDoorIndex >= gameMaze.length) ||
					!(currentRoom.isDoorLocked(Direction.WEST) == westDoorIndex < 0)
				)
				{
					eachRoomHasFourDoors = false;
					assertTrue(eachRoomHasFourDoors);
				}
			}
		}
		assertTrue(eachRoomHasFourDoors);
	}
	
	@Test
	void setEnteranceExit_playerXNotBelowZeroOrAbove4_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			Maze.setEnteranceExit(mazeRows, mazeColumns);
			if(Maze.getPlayerX() < 0 && Maze.getPlayerX() >= mazeColumns)
			{
				assertTrue(Maze.getPlayerX() >= 0 && Maze.getPlayerX() < mazeColumns);
			}
		}
	}
	
	@Test
	void setEnteranceExit_playerYNotBelowZeroOrAbove4_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			Maze.setEnteranceExit(mazeRows, mazeColumns);
			if(Maze.getPlayerX() < 0 && Maze.getPlayerX() >= mazeColumns)
			{
				assertTrue(Maze.getPlayerY() >= 0 && Maze.getPlayerY() < mazeRows);
			}
		}
	}
	
	@Test
	void setEnteranceExit_exitXNotBelowZeroOrAbove4_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			Maze.setEnteranceExit(mazeRows, mazeColumns);
			if(Maze.getPlayerX() < 0 && Maze.getPlayerX() >= mazeColumns)
			{
				assertTrue(Maze.getExitX() >= 0 && Maze.getExitX() < mazeColumns);
			}
		}
	}
	
	@Test
	void setEnteranceExit_exitYNotBelowZeroOrAbove4_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			Maze.setEnteranceExit(mazeRows, mazeColumns);
			if(Maze.getPlayerX() < 0 && Maze.getPlayerX() >= mazeColumns)
			{
				assertTrue(Maze.getExitY() >= 0 && Maze.getExitY() < mazeRows);
			}
		}
	}
	
	@Test
	void setEnteranceExit_playerAndMazeAreNotInSameIndex_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			Maze.setEnteranceExit(mazeRows, mazeColumns);
			if(Maze.getPlayerX() == Maze.getExitX() && Maze.getPlayerY() == Maze.getExitY())
			{
				assertTrue(!(Maze.getPlayerX() == Maze.getExitX() && Maze.getPlayerY() == Maze.getExitY()));			}
		}
	}
	
	// TODO test method Maze.createQuestionDatabase once enum implementation
	
	@Test
	void setPlayerLocation_worksForAllMazeIndexes_True()
	{
		for(int playerX = 0; playerX < mazeRows; playerX++)
		{
			for(int playerY = 0; playerY < mazeColumns; playerY++)
			{
				Maze.setPlayerLocation(playerX, playerY);
				if(Maze.getPlayerX() != playerX || Maze.getPlayerY() != playerY)
				{
					assertEquals(playerX, Maze.getPlayerX());
					assertEquals(playerY, Maze.getPlayerY());
				}
			}
		}
	}

}
