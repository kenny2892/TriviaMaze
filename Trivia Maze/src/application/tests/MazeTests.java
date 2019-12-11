package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Maze;
import application.Room;
import application.enums.Direction;

class MazeTests
{
	@Test
	void mazeConstructor_correctRowLength_TRUE()
	{
		Maze sut = new Maze(5, 5);
		assertTrue((sut.getMaze()).length == 5);
	}

	@Test
	void mazeConstructor_correctColumnLength_TRUE()
	{
		Maze sut = new Maze(5, 5);
		assertTrue((sut.getMaze())[0].length == 5);
	}

	@Test
	void mazeConstructor_defaultValueOfCurrentDirectionIsOfTypeNull_TRUE()
	{
		Maze sut = new Maze(5, 5);
		assertTrue(sut.getCurrentDirection() == Direction.NULL);
	}

	@Test
	void mazeConstructor_gameMazeHasNoNullRooms_TRUE()
	{
		Maze sut = new Maze(5, 5);
		Room[][] gameMaze = sut.getMaze();
		boolean trigger = true;
		for(int rowIndex = 0; rowIndex < 5; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < 5; columnIndex++)
			{
				if (gameMaze[rowIndex][columnIndex] == null)
					trigger = false;
			}
		}
		assertTrue(trigger);
	}

	@Test
	void mazeConstructor_eachRoomHasFourDoors_TRUE()
	{
		Maze sut = new Maze(5, 5);
		Room[][] gameMaze = sut.getMaze();
		boolean trigger = true;
		for(int rowIndex = 0; rowIndex < 5; rowIndex++)
		{
			for(int columnIndex = 0; columnIndex < 5; columnIndex++)
			{

				int northDoorIndex = rowIndex - 1;
				int southDoorIndex = rowIndex + 1;
				int eastDoorIndex = columnIndex + 1;
				int westDoorIndex = columnIndex - 1;

				Room currentRoom = gameMaze[rowIndex][columnIndex];

				if (!(currentRoom.isDoorLocked(Direction.NORTH) == northDoorIndex < 0)
						|| !(currentRoom.isDoorLocked(Direction.SOUTH) == southDoorIndex >= 5)
						|| !(currentRoom.isDoorLocked(Direction.EAST) == eastDoorIndex >= 5)
						|| !(currentRoom.isDoorLocked(Direction.WEST) == westDoorIndex < 0))
				{
					trigger = false;
					assertTrue(trigger);
				}
			}
		}
		assertTrue(trigger);
	}

	@Test
	void updateMazeRooms_throwsIllegalArgumentExceptionIfXOutOFBoundsOfGameMaze_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Maze sut = new Maze(5, 5);
			sut.updateMazeRooms(-1, 0, true);
		});
		assertEquals("Passed X value is out of the bounds of the maze.", exception.getMessage());
	}

	@Test
	void updateMazeRooms_throwsIllegalArgumentExceptionIfYOutOFBoundsOfGameMaze_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Maze sut = new Maze(5, 5);
			sut.updateMazeRooms(0, -1, true);
		});
		assertEquals("Passed Y value is out of the bounds of the maze.", exception.getMessage());
	}

	@Test
	void updateMazeRooms_returnsTrue_TRUE()
	{
		Maze sut = new Maze(5, 5);
		sut.setDirection(Direction.NORTH);
		assertTrue(sut.updateMazeRooms(2, 2, true));
	}

	@Test
	void setDirection_throwsIllegalArgumentExceptionIfPassedNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Maze sut = new Maze(5, 5);
			sut.setDirection(null);
		});
		assertEquals("Direction was null", exception.getMessage());
	}

	@Test
	void setExitX_throwsIllegalArgumentExceptionIfPassedNegativeOne_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Maze sut = new Maze(5, 5);
			sut.setExitX(-1);
		});
		assertEquals("Passed X value is out of the bounds of the maze.", exception.getMessage());
	}

	@Test
	void setExitX_throwsIllegalArgumentExceptionIfPassedSix_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Maze sut = new Maze(5, 5);
			sut.setExitX(6);
		});
		assertEquals("Passed X value is out of the bounds of the maze.", exception.getMessage());
	}

	@Test
	void setExitY_throwsIllegalArgumentExceptionIfPassedNegativeOne_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Maze sut = new Maze(5, 5);
			sut.setExitY(-1);
		});
		assertEquals("Passed Y value is out of the bounds of the maze.", exception.getMessage());
	}

	@Test
	void setExitY_throwsIllegalArgumentExceptionIfPassedSix_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Maze sut = new Maze(5, 5);
			sut.setExitY(6);
		});
		assertEquals("Passed Y value is out of the bounds of the maze.", exception.getMessage());
	}

	@Test
	void setExit_valueOfExitYIsNegativeOne_TRUE()
	{
		Maze sut = new Maze(5, 5);
		int yValue = sut.getExitY();
		assertTrue(yValue >= 0 && yValue < 5);
	}

	@Test
	void setExit_valueOfExitXIsNegativeOne_TRUE()
	{
		Maze sut = new Maze(5, 5);
		int xValue = sut.getExitX();
		assertTrue(xValue >= 0 && xValue < 5);
	}
}
