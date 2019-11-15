package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final int ROWS = 5, COLS = 5;
	private static Maze gameMaze;
	private static Player player;
	private static Database database;
	private static Stage stage;
	private static boolean isMaximized = true;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/MapAndQuestions.fxml"));
			Scene scene = new Scene(root);
			stage = primaryStage;
			
			setStage(scene);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		setUp();
		launch(args);
	}

	public static void setUp()
	{
		gameMaze = new Maze(ROWS, COLS);
		player = new Player();
		database = new Database();

		gameMaze.setExit(ROWS, COLS);

		do
		{
			player.setSpawn(ROWS, COLS);
		} while(player.getPlayerX() == gameMaze.getExitX() && player.getPlayerY() == gameMaze.getExitY());
	}

	private static void setStage(Scene scene)
	{
		if(stage == null || scene == null)
			return;
		
		else if(stage.getScene() != null)
			isMaximized = stage.isMaximized();
		
		stage.setScene(scene);		
		stage.sizeToScene();
		stage.show();
		
		if(isMaximized)
			stage.setMaximized(true);
	}
	
	public static void changeScene(SceneType type)
	{
		try
		{
			Parent root = null;

			switch(type)
			{
				case MAP:
					root = FXMLLoader.load(Main.class.getResource("/application/views/MapAndQuestions.fxml"));
					break;

				case CUSTOMIZE:
					root = FXMLLoader.load(Main.class.getResource("/application/views/Customize.fxml"));
					break;

				case SETTINGS:
					root = FXMLLoader.load(Main.class.getResource("/application/views/Settings.fxml"));
					break;

				case HELP:
					root = FXMLLoader.load(Main.class.getResource("/application/views/Settings.fxml"));
					break;

				case WIN:
					root = FXMLLoader.load(Main.class.getResource("/application/views/Win.fxml"));
					break;
					
				default:
					return;
			}

			setStage(new Scene(root));
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static boolean checkAnswer(int chosenAnswer)
	{
		boolean correct = database.checkAnswer(chosenAnswer);
		gameMaze.updateMazeRooms(player.getPlayerX(), player.getPlayerY(), !correct);

		return correct;
	}

	public static Question getQuestion(Direction direction)
	{
		if (direction == null)
			return null;

		gameMaze.setDirection(direction);

		return database.getQuestion();
	}

	public static Maze getMaze()
	{
		return gameMaze;
	}

	public static Player getPlayer()
	{
		return player;
	}
}
