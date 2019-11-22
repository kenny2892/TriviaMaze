package application;

import java.io.IOException;

import application.controllers.MapAndQuestionsController;
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

	private static Scene map, customize, help, settings, win;
	private static MapAndQuestionsController controller;
	
	@Override
	public void start(Stage primaryStage)
	{
		stage = primaryStage;
		changeScene(SceneType.MAP);
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
		
		stage.setScene(scene);		
		stage.sizeToScene();
		stage.setResizable(false);
		stage.setMaximized(true);
		stage.show();
	}
	
	public static void changeScene(SceneType type)
	{
		try
		{
			switch(type)
			{
				case MAP:
					if(map == null || controller == null)
					{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Main.class.getResource("/application/views/MapAndQuestions.fxml"));
						Parent root = loader.load();
						map = new Scene(root);
						controller = loader.getController();
					}
					
					setStage(map);
					controller.update();
					break;

				case CUSTOMIZE:
					if(customize == null)
						customize = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Customize.fxml")));
					
					setStage(customize);
					break;

				case SETTINGS:
					if(settings == null)
						settings = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Settings.fxml")));
					
					setStage(settings);
					break;

				case HELP:
					if(help == null)
						help = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Help.fxml")));
					
					setStage(help);
					break;

				case WIN:
					if(win == null)
						win = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Win.fxml")));
					
					setStage(win);
					break;
			}
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
