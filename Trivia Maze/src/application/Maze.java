package application;

import java.util.ArrayList;
import java.util.Random;

public class Maze
{
	private Room[][] gameMaze;
	private Player player;
	private Question currentQuestion;
	private Direction currentDirection;
	private Database database;
	private int mazeRows;
	private int mazeColumns;
	private int exitX;
	private int exitY;
	
	public Maze(int mazeRows, int mazeColumns)
	{
		this.gameMaze = createMaze(mazeRows, mazeColumns);
		this.player = new Player();
		this.currentDirection = Direction.NULL;
		this.database = new Database();
		this.mazeRows = mazeRows;
		this.mazeColumns = mazeColumns;
		this.exitX = -1;
		this.exitY = -1;
		setEnteranceExit(mazeRows, mazeColumns);
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
	
	public Question getQuestion()
	{
		return this.currentQuestion;
	}
	
	public Direction getCurrentDirection()
	{
		return currentDirection;
	}
	
	
	public Room getCurrRoom()
	{
		return this.gameMaze[this.player.getPlayerY()][this.player.getPlayerX()];
	}
	
	public void setDirection(Direction newDirection)
	{
		this.currentDirection = newDirection;
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
	
	public void setPlayerLocation(int newPlayerX, int newPlayerY)
	{
		player.setPlayerX(newPlayerX);
		player.setPlayerY(newPlayerY);
	}
	
	public void setEnteranceExit(final int mazeRows, final int mazeColumns)
	{
		Random rnjesus = new Random();
		this.setExitX(rnjesus.nextInt(mazeColumns));
		this.setExitY(rnjesus.nextInt(mazeRows));
		
		do
		{
			setPlayerLocation(rnjesus.nextInt(mazeColumns), rnjesus.nextInt(mazeRows));
		} while (player.getPlayerX() == exitX && player.getPlayerY() == exitY);

	}
	
	public Question getQuestion(Direction direction)
	{
		if(direction == null)
			return null;
		
		ArrayList<Question> questions = database.getQuestions();
		
		Question toUse = questions.get(0);
		questions.remove(0);
		
		currentDirection = direction;
		currentQuestion = toUse;
		
		return toUse;
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
				setPlayerLocation(player.getPlayerX() - 1, player.getPlayerY());
				break;
		default:
			throw new IllegalArgumentException("Passed value was NULL");
		}
		
		if(player.getPlayerX() == exitX && player.getPlayerY() == exitY)
			return true;
		
		return false;
	}
	
	public boolean checkAnswer(int chosenAnswer)
	{
		if(currentQuestion == null)
			return false;

		boolean correct = true;
		
		correct = currentQuestion.getCorrectIndex() == chosenAnswer;		
		updateMazeRooms(!correct);
		
		if(!correct)
			return correct;
		
		return true;
	}
	
	private void updateMazeRooms(boolean isLocked)
	{
		int playerX = player.getPlayerX();
		int playerY = player.getPlayerY();
		Room currentRoom = getCurrRoom();
		currentRoom.setDoorLock(currentDirection, isLocked);
		gameMaze[playerY][playerX] = currentRoom;
		
		Room connectedRoom = null;
		switch(currentDirection)
		{
			case NORTH:
				if(playerY - 1 >= 0)
				{
					connectedRoom = gameMaze[playerY - 1][playerX];
					connectedRoom.setDoorLock(Direction.SOUTH, isLocked);
					gameMaze[playerY - 1][playerX] = connectedRoom;
				}
				break;
				
			case SOUTH:
				if(playerY + 1 < gameMaze[0].length)
				{
					connectedRoom = gameMaze[playerY + 1][playerX];
					connectedRoom.setDoorLock(Direction.NORTH, isLocked);
					gameMaze[playerY + 1][playerX] = connectedRoom;
				}
				break;
				
			case EAST:
				if(playerX + 1 < gameMaze.length)
				{
					connectedRoom = gameMaze[playerY][playerX + 1];
					connectedRoom.setDoorLock(Direction.WEST, isLocked);
					gameMaze[playerY][playerX + 1] = connectedRoom;
				}
				break;
				
			case WEST:
				if(playerX - 1 >= 0)
				{
					connectedRoom = gameMaze[playerY][playerX - 1];
					connectedRoom.setDoorLock(Direction.EAST, isLocked);
					gameMaze[playerY][playerX - 1] = connectedRoom;
				}
				break;
		default:
			break;
		}
	}
	
	class Player
	{
		private int playerX;
		private int playerY;
		
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

	}

}
