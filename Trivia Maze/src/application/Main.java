package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final int ROWS = 5, COLS = 5;
	private static Maze gameMaze;
	private static Player player;
	private static Database database;
	
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
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
