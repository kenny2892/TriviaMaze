package application;

public class Maze
{
	private Room[][] gameMaze;
	private Player player;
	private int mazeRows;
	private int mazeColumns;
	private int exitX;
	private int exitY;
	
	public Maze(int mazeRows, int mazeColumns)
	{
		this.gameMaze = createMaze(mazeRows, mazeColumns);
		this.player = new Player();
		this.mazeRows = mazeRows;
		this.mazeColumns = mazeColumns;
		this.exitX = -1;
		this.exitY = -1;
	}
	
	public int getExitX()
	{
		return this.exitX;
	}
	
	public int getExitY()
	{
		return this.exitY;
	}
	
	public int getmazeRows()
	{
		return this.mazeRows;
	}
	
	public int getMazeColulumns()
	{
		return this.mazeColumns;
	}
	
	public Room[][] getMaze()
	{
		return this.gameMaze;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public Room getCurrRoom()
	{
		return this.gameMaze[this.player.getPlayerY()][this.player.getPlayerY()];
	}
	
	public void setExitX(int exitX) {
		if(exitX < 0 || exitX >= this.mazeRows)
		{
			throw new IllegalArgumentException("Passed X value is out of the bounds of the maze.");
		}
		this.exitX = exitX;
	}
	
	public void setExitY(int exitY)
	{
		if(exitX < 0 || exitX >= this.mazeRows)
		{
			throw new IllegalArgumentException("Passed Y value is out of the bounds of the maze.");
		}
		this.exitY = exitY;
	}
	
	public Room[][] createMaze(int rows, int cols)
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
				
				maze[row][col] = new Room(new Door(nIndex < 0), new Door(sIndex >= rows), new Door(eIndex >= cols), new Door(wIndex < 0));
			}
		}
		
		return maze;
	}
	
	public boolean movePlayer(Direction direction)
	{
		switch(direction)
		{
			case NORTH:
				setPlayerLocation(player.getPlayerX(), player.getPlayerY() - 1);
				break;
				
			case SOUTH:
				setPlayerLocation(player.getPlayerX(), player.getPlayerY() + 1);
				break;
				
			case EAST:
				setPlayerLocation(player.getPlayerX() + 1, player.getPlayerY());
				break;
				
			case WEST:
				setPlayerLocation(player.getPlayerX() - 1, player.getPlayerY() - 1);
				break;
		default:
			throw new IllegalArgumentException("Passed value was NULL");
		}
		
		if(player.getPlayerX() == exitX && player.getPlayerY() == exitY)
			return true;
		
		return false;
	}
	
	public void setPlayerLocation(int newPlayerX, int newPlayerY)
	{
		player.setPlayerX(newPlayerX);
		player.setPlayerY(newPlayerY);
	}
	
	private class Player
	{
		private int playerX;
		private int playerY;
		private Direction currentDirection;
		
		public Player()
		{
			this.playerX = -1;
			this.playerY = -1;
		}
		
		public int getPlayerX()
		{
			return this.playerX;
		}
		
		public int getPlayerY()
		{
			return this.playerY;
		}
		
		public Direction getCurrentDirection()
		{
			return currentDirection;
		}
		
		public void setPlayerX(int playerX)
		{
			if(playerX < 0 || playerX > gameMaze.length)
			{
				throw new IllegalArgumentException("Passed X value is out of the bounds of the maze.");
			}
			this.playerX = playerX;
		}
		
		public void setPlayerY(int playerY)
		{
			if(playerY < 0 || playerY > gameMaze.length)
			{
				throw new IllegalArgumentException("Passed Y value is out of the bounds of the maze.");
			}
			this.playerY = playerY;
		}
		
		public void setDirection(Direction newDirection)
		{
			this.currentDirection = newDirection;
		}

	}

}
