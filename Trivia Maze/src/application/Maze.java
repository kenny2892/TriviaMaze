package application;

import java.io.Serializable;
import java.util.Random;

import application.enums.Direction;

public class Maze implements Serializable
{
	private static final long serialVersionUID = 9201369801085494420L;
	private Room[][] gameMaze;
	private Direction currentDirection;
	private int exitX;
	private int exitY;

	public Maze(int mazeRows, int mazeColumns)
	{
		this.gameMaze = createMaze(mazeRows, mazeColumns);
		this.currentDirection = Direction.NULL;
		this.exitX = -1;
		this.exitY = -1;
		setExit(mazeRows, mazeColumns);
	}
	
	private Room[][] createMaze(int rows, int cols)
	{
		Room[][] maze = new Room[rows][cols];

		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				int nIndex = row - 1;
				int sIndex = row + 1;
				int eIndex = col + 1;
				int wIndex = col - 1;

				maze[row][col] = new Room(new Door(nIndex < 0), new Door(sIndex >= rows), new Door(eIndex >= cols),
						new Door(wIndex < 0));
			}
		}

		return maze;
	}

	public boolean updateMazeRooms(int x, int y, boolean isLocked)
	{
		if (x < 0 || x >= 5)
			throw new IllegalArgumentException("Passed X value is out of the bounds of the maze.");
		else if (y < 0 || y >= 5)
			throw new IllegalArgumentException("Passed Y value is out of the bounds of the maze.");
		
		Room currentRoom = getRoom(x, y);
		currentRoom.setDoorLock(currentDirection, isLocked);
		gameMaze[y][x] = currentRoom;

		Room connectedRoom = null;
		switch(currentDirection)
		{
			case NORTH:
				if (y - 1 >= 0)
				{
					connectedRoom = gameMaze[y - 1][x];
					connectedRoom.setDoorLock(Direction.SOUTH, isLocked);
					gameMaze[y - 1][x] = connectedRoom;
				}
				break;

			case SOUTH:
				if (y + 1 < gameMaze[0].length)
				{
					connectedRoom = gameMaze[y + 1][x];
					connectedRoom.setDoorLock(Direction.NORTH, isLocked);
					gameMaze[y + 1][x] = connectedRoom;
				}
				break;

			case EAST:
				if (x + 1 < gameMaze.length)
				{
					connectedRoom = gameMaze[y][x + 1];
					connectedRoom.setDoorLock(Direction.WEST, isLocked);
					gameMaze[y][x + 1] = connectedRoom;
				}
				break;

			case WEST:
				if (x - 1 >= 0)
				{
					connectedRoom = gameMaze[y][x - 1];
					connectedRoom.setDoorLock(Direction.EAST, isLocked);
					gameMaze[y][x - 1] = connectedRoom;
				}
				break;
				
			default:
				break;
		}
		
		return checkMaze(y, x, new int[5][5]);
	}
	
	private boolean checkMaze(int playerY, int playerX, int[][] traveledRooms)
	{
		boolean result = false;
		
		if(isValidCoord(playerY, playerX, traveledRooms))
		{
			if(playerY == exitY && playerX == exitX)
				return true;
			
			Room curr = gameMaze[playerY][playerX];
			traveledRooms[playerY][playerX] = 1;
			
			if(!result)
			{
				if(curr.isDoorLocked(Direction.SOUTH))
					result = false;
				
				else
					result = checkMaze(playerY + 1, playerX, traveledRooms);
			}
			
			if(!result)
			{
				if(curr.isDoorLocked(Direction.NORTH))
					result = false;
				
				else
					result = checkMaze(playerY - 1, playerX, traveledRooms);
			}
			
			if(!result)
			{
				if(curr.isDoorLocked(Direction.EAST))
					result = false;
				
				else
					result = checkMaze(playerY, playerX + 1, traveledRooms);
			}
			
			if(!result)
			{
				if(curr.isDoorLocked(Direction.WEST))
					result = false;
				
				else
					result = checkMaze(playerY, playerX - 1, traveledRooms);
			}
		}
		
		return result;
	}
	
	private boolean isValidCoord(int row, int col, int[][] traveledRooms)
	{
		if(gameMaze != null && row < gameMaze.length && col < gameMaze[0].length &&  row > -1 && col > -1 && traveledRooms[row][col] == 0)
			return true;
		
		return false;
	}

	public int getExitX()
	{
		return this.exitX;
	}

	public int getExitY()
	{
		return this.exitY;
	}

	public Room[][] getMaze()
	{
		return this.gameMaze;
	}

	public Direction getCurrentDirection()
	{
		return currentDirection;
	}

	public Room getRoom(int x, int y)
	{
		return this.gameMaze[y][x];
	}

	public void setDirection(Direction newDirection)
	{
		if(newDirection == null)
			throw new IllegalArgumentException("Direction was null");
		this.currentDirection = newDirection;
	}

	public void setExitX(int exitX)
	{
		if (exitX < 0 || exitX >= 5)
			throw new IllegalArgumentException("Passed X value is out of the bounds of the maze.");
		
		this.exitX = exitX;
	}

	public void setExitY(int exitY)
	{
		if (exitY < 0 || exitY >= 5)
			throw new IllegalArgumentException("Passed Y value is out of the bounds of the maze.");
		
		this.exitY = exitY;
	}

	public void setExit(final int mazeRows, final int mazeColumns)
	{
		Random rnjesus = new Random();
		this.setExitX(rnjesus.nextInt(mazeColumns));
		this.setExitY(rnjesus.nextInt(mazeRows));
	}
}
