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
		gameMaze = Maze.createMaze(mazeRows, mazeColumns);
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
	void isMazeColumns_ofLengthaMazeColumns_mazeColumns()
	{
		assertEquals(mazeColumns, gameMaze.length);
	}
	
	@Test
	void mazeRowLength()
	{
		assertEquals(mazeRows, gameMaze[0].length);
	}
	
	@Test
	void eachElementIsARoom()
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
	void eachRoomHasFourDoors()
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
	void playerAndExitAreNotInTheSameLocation()
	{
		Maze.setEnteranceExit(mazeRows, mazeColumns);
		assertTrue(!(Maze.getPlayerX() == Maze.getExitX() && Maze.getPlayerY() == Maze.getExitY()));
	}
	
	@Test
	void enteranceAndExitVariablesAreNotZeroOrAbove()
	{
		assertTrue(Maze.getExitX() < 0);
		assertTrue(Maze.getExitY() < 0);
		assertTrue(Maze.getPlayerX() < 0);
		assertTrue(Maze.getPlayerY() < 0);
	}
	
	@Test
	void enteranceAndExitAreWithinMaze()
	{
		Maze.setEnteranceExit(mazeRows, mazeColumns);
		assertTrue(Maze.getExitX() >= 0 && Maze.getExitX() < mazeColumns);
		assertTrue(Maze.getExitY() >= 0 && Maze.getExitY() < mazeRows);
		assertTrue(Maze.getPlayerX() >= 0 && Maze.getPlayerX() < mazeColumns);
		assertTrue(Maze.getPlayerY() >= 0 && Maze.getPlayerY() < mazeRows);
	}
	
	@Test
	void testPlayerOutOfBoundsXAxis()
	{
		
	}

}
