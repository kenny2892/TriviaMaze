package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Maze extends Application
{
	private static int playerX = -1;
	private static int playerY = -1;
	
	private static Room[][] gameMaze;
	private static ArrayList<Question> questions;
	private static Direction currentDirection;
	private static Question currentQuestion;
	
	private static int exitX = -1; // TODO EXIT
	private static int exitY = -1;
	
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("/Main/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
//			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();

			double height = scene.getHeight();
			double width = scene.getWidth();

			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			double newX = ((screenBounds.getWidth() - width) / 2);
			double newY = ((screenBounds.getHeight() - height) / 2);

			primaryStage.setX(newX);
			primaryStage.setY(newY);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		final int mazeRows = 5;
		final int mazeColumns = 5;
		gameMaze = createMaze(mazeRows, mazeColumns);
		questions = createQuestionDatabase();
		setEnteranceExit(mazeRows, mazeColumns);
		
		launch(args);
	}
	
	public static Room[][] createMaze(int rows, int cols)
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
	
	public static ArrayList<Question> createQuestionDatabase() // TODO add switch and enum for different databases
	{
		ArrayList<Question> array = new ArrayList<Question>();
		
		try
		{
			Connection connect = JavaDatabaseConnection.dbConnector();
			
			array = getMultipleChoice(connect, array);
			array = getTrueFalse(connect, array);
			
			while(array.size() <= 40) // TODO For Demo Only! Remove later
			{
				array = getMultipleChoice(connect, array);
				array = getTrueFalse(connect, array);
			}
			
			Collections.shuffle(array);
			
			connect.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		return array;
	}
	
	private static ArrayList<Question> getMultipleChoice(Connection connect, ArrayList<Question> array)
	{
		try
		{
			String query = "Select * from MultipleChoiceQuestions";
			PreparedStatement pst = connect.prepareStatement(query);
			
			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String question = results.getString("Questions");
				String options = results.getString("Options");
				String answer = results.getString("Answers");
				
				ArrayList<String> optionAra = new ArrayList<String>(Arrays.asList(options.split("\n")));
				
				int indexOfAnswer = 0;
				
				for(int i = 0; i < optionAra.size(); i++)
				{
					if(optionAra.get(i).startsWith(answer))
						indexOfAnswer = i;
					
					optionAra.set(i, optionAra.get(i).substring(3));
				}
				
				MultipleChoiceQuestion mcQuestion = new MultipleChoiceQuestion(question, optionAra, indexOfAnswer);
				array.add(mcQuestion);
			}

			results.close();
			pst.close();
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		
		return array;
	}
	
	private static ArrayList<Question> getTrueFalse(Connection connect, ArrayList<Question> array)
	{
		try
		{
			String query = "Select * from TrueFalseQuestions";
			PreparedStatement pst = connect.prepareStatement(query);
			
			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String question = results.getString("Questions");
				String answer = results.getString("Answers");
				
				ArrayList<String> options = new ArrayList<String>();
				options.add("True");
				options.add("False");
				
				int index = 0;
				if(answer.compareTo("False") == 0)
					index = 1;
				
				TrueFalseQuestion tfQuestion = new TrueFalseQuestion(question, options, index);
				array.add(tfQuestion);
			}
			
			results.close();
			pst.close();
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		
		return array;
	}
	
	public static void setEnteranceExit(final int mazeRows, final int mazeColumns)
	{
		Random rnjesus = new Random();
		exitX = rnjesus.nextInt(mazeColumns);
		exitY = rnjesus.nextInt(mazeRows);
		
		do
		{
			playerX = rnjesus.nextInt(mazeColumns);
			playerY = rnjesus.nextInt(mazeRows);
		} while (playerX == exitX && playerY == exitY);

	}
	
	public static void setPlayerLocation(int x, int y)
	{
		if(x < 0)
			throw new IllegalArgumentException("Passed X was negative.");

		else if(y < 0)
			throw new IllegalArgumentException("Passed Y was negative.");
		
		else if(x >= gameMaze[0].length)
			throw new IllegalArgumentException("Passed X was greater than the maze's column count.");
		
		else if(y >= gameMaze.length)
			throw new IllegalArgumentException("Passed Y was greater than the maze's row count.");
		
		playerX = x;
		playerY = y;
	}
	
	public static Direction getCurrentDirection()
	{
		return currentDirection;
	}
	
	public static Room getCurrRoom()
	{
		return gameMaze[playerY][playerX];
	}
	
	public static int getPlayerX()
	{
		return playerX;
	}
	
	public static int getPlayerY()
	{
		return playerY;
	}
	
	public static int getExitX()
	{
		return exitX;
	}
	
	public static int getExitY()
	{
		return exitY;
	}
	
	public static Question getQuestion(Direction direction)
	{
		if(direction == null)
			return null;
		
		Question toUse = questions.get(0);
		questions.remove(0);
		
		currentDirection = direction;
		currentQuestion = toUse;
		
		return toUse;
	}
	
	public static boolean checkAnswer(int chosenAnswer)
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
	
	public static boolean movePlayer(Direction direction)
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
		}
		
		if(playerX == exitX && playerY == exitY)
			return true;
		
		return false;
	}
	
	private static void updateMazeRooms(boolean isLocked)
	{
		Room curr = getCurrRoom();
		curr.setDoorLock(currentDirection, isLocked);
		gameMaze[playerY][playerX] = curr;
		
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
		}
	}
}
