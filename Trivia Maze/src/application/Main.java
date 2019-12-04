package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import application.controllers.MapController;
import application.controllers.MultipleChoiceQuestionController;
import application.controllers.SoundQuestionController;
import application.controllers.TrueFalseQuestionController;
import application.controllers.VideoQuestionController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final int ROWS = 5, COLS = 5;
	private static Maze gameMaze;
	private static Player player;
	private static Database database;
	private static ArchwayStatus[][] horizontalArchways, verticalArchways;
	private static File saveLoadDir;
	
	private static Stage stage;
	private static SceneType currentScene;
	private static Scene start, map, customize, help, settings, win, lose, trueFalse, mcQuestions, videoQuestions, soundQuestions;
	private static MapController mapController;
	private static MultipleChoiceQuestionController mcController;
	private static TrueFalseQuestionController tfController;
	private static VideoQuestionController videoController;
	private static SoundQuestionController soundController;
	
	@Override
	public void start(Stage primaryStage)
	{
		stage = primaryStage;
		changeScene(SceneType.START);
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static void setUp(DatabaseType type)
	{
		if(type == null)
			throw new IllegalArgumentException("Null Database Type");
		
		else if(gameMaze != null)
			return;
		
		gameMaze = new Maze(ROWS, COLS);
		player = new Player();
		database = new Database(type);
		
		horizontalArchways = new ArchwayStatus[5][4];
		verticalArchways = new ArchwayStatus[4][5];
		
		for(ArchwayStatus[] archways : horizontalArchways)
			Arrays.fill(archways, ArchwayStatus.UNOPENED);
		
		for(ArchwayStatus[] archways : verticalArchways)
			Arrays.fill(archways, ArchwayStatus.UNOPENED);

		gameMaze.setExit(ROWS, COLS);
		
		String home = System.getProperty("user.home");
		saveLoadDir = new File(home + "/Documents/");

		do
		{
			player.setSpawn(ROWS, COLS);
		} while(player.getPlayerX() == gameMaze.getExitX() && player.getPlayerY() == gameMaze.getExitY());
		
		changeScene(SceneType.MAP);
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
						
						keyBindMap();
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
					
				case START:
					if(start == null)
						start = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Start.fxml")));
					
					setStage(start);
					currentScene = SceneType.START;
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
	
	public static ArchwayStatus[][] getHorizontalArchways()
	{
		return horizontalArchways;
	}
	
	public static ArchwayStatus[][] getVerticalArchways()
	{
		return verticalArchways;
	}
	
	public static void setHorizontalArchway(int y, int x, ArchwayStatus status)
	{
		if(status == null)
			throw new IllegalArgumentException("Null Archway Status");
		
		else if(y >= ROWS || y < 0)
			throw new IllegalArgumentException("Invalid y value");
		
		else if(x >= COLS || x < 0)
			throw new IllegalArgumentException("Invalid x value");
		
		horizontalArchways[y][x] = status;
	}
	
	public static void setVerticalArchway(int y, int x, ArchwayStatus status)
	{
		if(status == null)
			throw new IllegalArgumentException("Null Archway Status");
		
		else if(y >= ROWS || y < 0)
			throw new IllegalArgumentException("Invalid y value");
		
		else if(x >= COLS || x < 0)
			throw new IllegalArgumentException("Invalid x value");
		
		verticalArchways[y][x] = status;
	}
	
	public static boolean save()
	{
		if (stage == null)
			return false;

		File fileToSaveTo = null;
		
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Choose a Save Directory");
		chooser.setInitialDirectory(saveLoadDir);

		File temp = chooser.showDialog(stage);
		
		if(temp != null)
		{
			saveLoadDir = temp;
			
			fileToSaveTo = new File(temp.getPath() + "\\saveData.save");
			int num = 1;
			
			while(fileToSaveTo.exists())
			{
				fileToSaveTo = new File(temp.getPath() + "\\saveData" + num + ".save");
				num++;
			}
		}
		
		else
			return false;
		
		try 
		{
			ArrayList<Object> itemsToSave = new ArrayList<Object>();
			itemsToSave.add(gameMaze);
			itemsToSave.add(player);
			itemsToSave.add(database);
			itemsToSave.add(horizontalArchways);
			itemsToSave.add(verticalArchways);
			
			FileOutputStream fileOutStream = new FileOutputStream(fileToSaveTo);
			ObjectOutputStream out = new ObjectOutputStream(fileOutStream);
			
			out.writeObject(itemsToSave);
			out.close();
			fileOutStream.close();
			return true;
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean load()
	{
		if (stage == null)
			return false;

		File fileToLoad = null;
		
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose a Save File");
		chooser.setInitialDirectory(saveLoadDir);
		
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Save fils (*.save)", "*.save");
		chooser.getExtensionFilters().add(filter);

		File temp = chooser.showOpenDialog(stage);
		
		if(temp != null)
		{
			fileToLoad = temp;
			saveLoadDir = new File(fileToLoad.getParent());
		}
		
		else if(temp == null || !fileToLoad.getPath().endsWith(".save"))
			return false;
		
		try
		{
			FileInputStream fileInStream = new FileInputStream(fileToLoad);
			ObjectInputStream in = new ObjectInputStream(fileInStream);
			
			Object objRead = in.readObject();
			
			if(objRead instanceof ArrayList<?>)
			{
				@SuppressWarnings("unchecked")
				ArrayList<Object> itemAra = (ArrayList<Object>) objRead;
				
				if(itemAra.get(0) instanceof Maze)
					gameMaze = (Maze) itemAra.get(0);
				
				if(itemAra.get(1) instanceof Player)
					player = (Player) itemAra.get(1);
				
				if(itemAra.get(2) instanceof Database)
					database = (Database) itemAra.get(2);
				
				if(itemAra.get(3) instanceof ArchwayStatus[][])
					horizontalArchways = (ArchwayStatus[][]) itemAra.get(3);
				
				if(itemAra.get(4) instanceof ArchwayStatus[][])
					verticalArchways = (ArchwayStatus[][]) itemAra.get(4);
			}
			
			in.close();
			fileInStream.close();
			mapController.loadMaze();
			
			return true;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static void keyBindMap()
	{
		if(map == null)
			return;
		
		map.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				switch(event.getCode())
				{
					case Q:
						mapController.customizeBtn();
						break;
						
					case W:
						mapController.saveBtn();
						break;
						
					case E:
						mapController.loadBtn();
						break;
						
					case R:
						mapController.settingsBtn();
						break;
						
					case T:
						mapController.helpBtn();
						break;
						
					case UP:
						mapController.nDoorBtn();
						break;
						
					case DOWN:
						mapController.sDoorBtn();
						break;
						
					case LEFT:
						mapController.wDoorBtn();
						break;
						
					case RIGHT:
						mapController.eDoorBtn();
						break;
						
					default:
						break;
				}
			}
		});
	}
	
	public static void openGitHub()
	{
		try
		{
			Desktop.getDesktop().browse(new URL("https://github.com/kenny2892/TriviaMaze").toURI());
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		catch(URISyntaxException e)
		{
			e.printStackTrace();
		}
	}
}
