package application;

import java.util.Random;

public class Player
{
	private int playerX;
	private int playerY;

	public Player()
	{
		this.playerX = -1;
		this.playerY = -1;
	}

	public void setSpawn(final int mazeRows, final int mazeColumns)
	{
		Random rnjesus = new Random();
		setPlayerLocation(rnjesus.nextInt(mazeColumns), rnjesus.nextInt(mazeRows));
	}

	public boolean movePlayer(Maze maze, Direction direction)
	{
		switch(direction)
		{
			case NORTH:
				setPlayerLocation(playerX, playerY - 1);
				break;

			case SOUTH:
				setPlayerLocation(playerX, playerY + 1);
				break;

			case EAST:
				setPlayerLocation(playerX + 1, playerY);
				break;

			case WEST:
				setPlayerLocation(playerX - 1, playerY);
				break;

			default:
				throw new IllegalArgumentException("Passed value was NULL");
		}

		if (playerX == maze.getExitX() && playerY == maze.getExitY())
			return true;

		return false;
	}

	public int getPlayerX()
	{
		return this.playerX;
	}

	public int getPlayerY()
	{
		return this.playerY;
	}

	public void setPlayerLocation(int newPlayerX, int newPlayerY)
	{
		setPlayerX(newPlayerX);
		setPlayerY(newPlayerY);
	}

	public void setPlayerX(int playerX)
	{
		if (playerX < 0 || playerX > Main.COLS)
			throw new IllegalArgumentException("Passed X value is out of the bounds of the maze.");
		
		this.playerX = playerX;
	}

	public void setPlayerY(int playerY)
	{
		if (playerY < 0 || playerY > Main.ROWS)
			throw new IllegalArgumentException("Passed Y value is out of the bounds of the maze.");
		
		this.playerY = playerY;
	}
}