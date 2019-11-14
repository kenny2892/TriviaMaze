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
import javafx.scene.image.ImageView;
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
	
	private static int exitX = -1;
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
				for(String option : optionAra)
				{
					if(!option.startsWith(answer))
						indexOfAnswer++;
					
					else
						break;
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
		return gameMaze[playerX][playerY];
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
		
		return toUse;
	}
	
	public static boolean checkAnswer(int chosenAnswer)
	{
		if(currentQuestion == null)
			return false;

		boolean correct = currentQuestion.getCorrectIndex() == chosenAnswer;
		
		Room curr = gameMaze[playerY][playerX];
		curr.setDoorLock(currentDirection, !correct);
		
		if(!correct)
			return correct;
		
		switch(currentDirection)
		{
			case NORTH:
				setPlayerLocation(playerX, playerY--);
				break;
				
			case SOUTH:
				setPlayerLocation(playerX, playerY++);
				break;
				
			case EAST:
				setPlayerLocation(playerX++, playerY);
				break;
				
			case WEST:
				setPlayerLocation(playerX--, playerY);
				break;
		}
		
		return true;
	}
}
