package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMazeTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		Maze.mazeBuilder(5, 5);
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
	void mazeColumnLength()
	{
		assertEquals(5, Maze.gameMaze.length);
	}
	
	@Test
	void mazeRowLength()
	{
		assertEquals(5, Maze.gameMaze[0].length);
	}
	
	@Test
	void eachElementIsARoom()
	{
		boolean eachElementIsARoom = true;
		for(int rowIndex = 0; rowIndex < Maze.gameMaze[0].length; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < Maze.gameMaze.length; columnIndex++)
			{
				if(!(Maze.gameMaze[rowIndex][columnIndex] instanceof Room))
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
		for(int rowIndex = 0; rowIndex < Maze.gameMaze[0].length; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < Maze.gameMaze.length; columnIndex++)
			{
				if
				(
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.NORTH) instanceof Door) ||
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.SOUTH) instanceof Door) ||
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.EAST) instanceof Door) ||
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.WEST) instanceof Door)
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
	void eachDoorHasAQuestion()
	{
		boolean eachDoorHasAQuestion = true;
		for(int rowIndex = 0; rowIndex < Maze.gameMaze[0].length; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < Maze.gameMaze.length; columnIndex++)
			{
				if
				(
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.NORTH).getQuestion() instanceof Question ||
					Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.NORTH).getQuestion() instanceof NullQuestion) ||
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.SOUTH).getQuestion() instanceof Question ||
					Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.SOUTH).getQuestion() instanceof NullQuestion) ||
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.EAST).getQuestion() instanceof Question ||
					Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.EAST).getQuestion() instanceof NullQuestion) ||
					!(Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.WEST).getQuestion() instanceof Question ||
					Maze.gameMaze[rowIndex][columnIndex].getDoor(Direction.WEST).getQuestion() instanceof NullQuestion)
				)
				{
					eachDoorHasAQuestion = false;
					assertTrue(eachDoorHasAQuestion);
				}
			}
		}
		assertTrue(eachDoorHasAQuestion);
	}

}
