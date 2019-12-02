package application;

import java.io.IOException;

import application.controllers.MapController;
import application.controllers.MultipleChoiceQuestionController;
import application.controllers.SoundQuestionController;
import application.controllers.TrueFalseQuestionController;
import application.controllers.VideoQuestionController;
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

	private static SceneType currentScene;
	private static Scene map, customize, help, settings, win, lose, trueFalse, mcQuestions, videoQuestions, soundQuestions;
	private static MapController mapController;
	private static MultipleChoiceQuestionController mcController;
	private static TrueFalseQuestionController tfController;
	private static VideoQuestionController videoController;
	private static SoundQuestionController soundController;
	
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
					if(map == null || mapController == null)
					{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Main.class.getResource("/application/views/Map.fxml"));
						Parent root = loader.load();
						map = new Scene(root);
						mapController = loader.getController();
					}
					
					setStage(map);
					mapController.update();
					currentScene = SceneType.MAP;
					break;

				case CUSTOMIZE:
					if(customize == null)
						customize = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Customize.fxml")));
					
					setStage(customize);
					currentScene = SceneType.CUSTOMIZE;
					break;

				case SETTINGS:
					if(settings == null)
						settings = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Settings.fxml")));
					
					setStage(settings);
					currentScene = SceneType.SETTINGS;
					break;

				case HELP:
					if(help == null)
						help = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Help.fxml")));
					
					setStage(help);
					currentScene = SceneType.HELP;
					break;

				case WIN:
					if(win == null)
						win = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Win.fxml")));
					
					setStage(win);
					currentScene = SceneType.WIN;
					break;

				case LOSE:
					if(lose == null)
						lose = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Lose.fxml")));
					
					setStage(lose);
					currentScene = SceneType.LOSE;
					break;

				case TRUE_FALSE:
					if(trueFalse == null)
					{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Main.class.getResource("/application/views/TrueFalseQuestion.fxml"));
						Parent root = loader.load();
						trueFalse = new Scene(root);
						tfController = loader.getController();
					}
					
					setStage(trueFalse);
					currentScene = SceneType.TRUE_FALSE;
					break;

				case MULTIPLE_CHOICE:
					if(mcQuestions == null)
					{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Main.class.getResource("/application/views/MultipleChoiceQuestion.fxml"));
						Parent root = loader.load();
						mcQuestions = new Scene(root);
						mcController = loader.getController();
					}
					
					setStage(mcQuestions);
					currentScene = SceneType.MULTIPLE_CHOICE;
					break;
					
				case SOUND:
					if(soundQuestions == null)
					{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Main.class.getResource("/application/views/SoundQuestion.fxml"));
						Parent root = loader.load();
						soundQuestions = new Scene(root);
						soundController = loader.getController();
					}
					
					setStage(soundQuestions);
					currentScene = SceneType.SOUND;
					break;
					
				case VIDEO:
					if(videoQuestions == null)
					{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Main.class.getResource("/application/views/VideoQuestion.fxml"));
						Parent root = loader.load();
						videoQuestions = new Scene(root);
						videoController = loader.getController();
					}
					
					setStage(videoQuestions);
					currentScene = SceneType.VIDEO;
					break;
			}
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void checkAnswer(int chosenAnswer)
	{
		boolean correct = database.checkAnswer(chosenAnswer);
		boolean canComplete = gameMaze.updateMazeRooms(player.getPlayerX(), player.getPlayerY(), !correct);
		
		if(!canComplete)
		{
			changeScene(SceneType.LOSE);
			return;
		}
		
		mapController.updateMaze(correct);
		
		if(currentScene != SceneType.WIN)
			changeScene(SceneType.MAP);
	}
	
	public static void showQuestion(Direction direction)
	{
		if (direction == null)
			return;

		gameMaze.setDirection(direction);
		
		Question question = database.getQuestion();
		
		switch(question.getQuestionsType())
		{
			case MULTIPLE_CHOICE:
				if(question instanceof MultipleChoiceQuestion)
				{
					changeScene(SceneType.MULTIPLE_CHOICE);
					mcController.setQuestion((MultipleChoiceQuestion) question);
				}
				break;			
			
			case TRUE_FALSE:
				if(question instanceof TrueFalseQuestion)
				{
					changeScene(SceneType.TRUE_FALSE);
					tfController.setQuestion((TrueFalseQuestion) question);
				}
				break;
				
			case SOUND:
				if(question instanceof SoundQuestion)
				{
					changeScene(SceneType.SOUND);
					soundController.setQuestion((SoundQuestion) question);
				}
				break;
				
			case VIDEO:
				if(question instanceof VideoQuestion)
				{
					changeScene(SceneType.VIDEO);
					videoController.setQuestion((VideoQuestion) question);
				}
				break;
		}
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
