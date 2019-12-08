package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import application.Maze;
import application.Player;
import application.Room;
import application.enums.Direction;

class MazeTests {
	
	private static int mazeRows;
	private static int mazeColumns;
	private static Player player;
	private static Maze gameMaze;

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
		
	}

	@BeforeEach
	void setUp() throws Exception
	{
		mazeRows = 5;
		mazeColumns = 5;
		gameMaze = new Maze(mazeRows, mazeColumns);
		player = new Player();
		
		gameMaze.setExit(mazeRows, mazeColumns);
		
		do
		{
			player.setSpawn(mazeRows, mazeColumns);
		} while(player.getPlayerX() == gameMaze.getExitX() && player.getPlayerY() == gameMaze.getExitY());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		
	}

	@Test
	void createMaze_correctColumnLength_5()
	{
		assertEquals(5, gameMaze.getMaze().length);
	}
	
	@Test
	void createMaze_corretRowLength_5()
	{
		assertEquals(5, gameMaze.getMaze()[0].length);
	}
	
	@Test
	void createMaze_eachElementIsARoom_True()
	{
		boolean eachElementIsARoom = true;
		for(int rowIndex = 0; rowIndex < gameMaze.getMaze()[0].length; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < gameMaze.getMaze().length; columnIndex++)
			{
				if(!(gameMaze.getMaze()[rowIndex][columnIndex] instanceof Room))
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
		for(int rowIndex = 0; rowIndex < gameMaze.getMaze()[0].length; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < gameMaze.getMaze().length; columnIndex++)
			{
				
				int northDoorIndex = rowIndex - 1;
				int southDoorIndex = rowIndex + 1;
				int eastDoorIndex = columnIndex + 1;
				int westDoorIndex = columnIndex - 1;
				
				Room currentRoom = gameMaze.getMaze()[rowIndex][columnIndex];
				
				if
				(
					!(currentRoom.isDoorLocked(Direction.NORTH) == northDoorIndex < 0) ||
					!(currentRoom.isDoorLocked(Direction.SOUTH) == southDoorIndex >= gameMaze.getMaze()[0].length) ||
					!(currentRoom.isDoorLocked(Direction.EAST) == eastDoorIndex >= gameMaze.getMaze().length) ||
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
			if(player.getPlayerX() < 0 && player.getPlayerX() >= mazeColumns)
			{
				assertTrue(player.getPlayerX() >= 0 && player.getPlayerX() < mazeColumns);
			}
		}
	}
	
	@Test
	void setEnteranceExit_playerYNotBelowZeroOrAbove4_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			if(player.getPlayerX() < 0 && player.getPlayerX() >= mazeColumns)
			{
				assertTrue(player.getPlayerY() >= 0 && player.getPlayerY() < mazeRows);
			}
		}
	}
	
	@Test
	void setEnteranceExit_exitXNotBelowZeroOrAbove4_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			if(player.getPlayerX() < 0 && player.getPlayerX() >= mazeColumns)
			{
				assertTrue(gameMaze.getExitX() >= 0 && gameMaze.getExitX() < mazeColumns);
			}
		}
	}
	
	@Test
	void setEnteranceExit_exitYNotBelowZeroOrAbove4_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			if(player.getPlayerX() < 0 && player.getPlayerX() >= mazeColumns)
			{
				assertTrue(gameMaze.getExitY() >= 0 && gameMaze.getExitY() < mazeRows);
			}
		}
	}
	
	@Test
	void setEnteranceExit_playerAndMazeAreNotInSameIndex_True()
	{
		for(int runs = 25; runs >= 0; runs--)
		{
			if(player.getPlayerX() == gameMaze.getExitX() && player.getPlayerY() == gameMaze.getExitY())
			{
				assertTrue(!(player.getPlayerX() == gameMaze.getExitX() && player.getPlayerY() == gameMaze.getExitY()));}
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
				player.setPlayerLocation(playerX, playerY);
				if(player.getPlayerX() != playerX || player.getPlayerY() != playerY)
				{
					assertEquals(playerX, player.getPlayerX());
					assertEquals(playerY, player.getPlayerY());
				}
			}
		}
	}

}
