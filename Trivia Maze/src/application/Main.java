// Cheat Code is: / + UP Arrow + UP Arrow + Down Arrow + Down Arrow + Left Arrow + Right Arrow + Left Arrow + Right Arrow + B + A

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
import application.controllers.SettingsController;
import application.controllers.SoundQuestionController;
import application.controllers.TrueFalseQuestionController;
import application.controllers.VideoQuestionController;
import application.enums.ArchwayStatus;
import application.enums.DatabaseType;
import application.enums.Direction;
import application.enums.SceneType;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final int ROWS = 5, COLS = 5;
	private static Maze gameMaze;
	private static Player player;
	private static Database database;
	private static DatabaseType dataType;
	private static ArchwayStatus[][] horizontalArchways, verticalArchways;
	private static KeyBindings keyBindings;
	private static File saveLoadDir;
	private static boolean cheatMode;
	private static boolean cheatDoor;
	private static boolean cheatAnswer;
	private static String enteredCheatCode;
	
	private static Stage stage;
	private static SceneType currentScene;
	private static Scene start, map, customize, help, settings, win, lose, trueFalse, mcQuestions, videoQuestions, soundQuestions, edit;
	private static MapController mapController;
	private static SettingsController settingsController;
	private static MultipleChoiceQuestionController mcController;
	private static TrueFalseQuestionController tfController;
	private static VideoQuestionController videoController;
	private static SoundQuestionController soundController;
	private static Media bgMusic;
	private static MediaPlayer bgPlayer;
	
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
		
		else if (stage == null)
			return;
		
		gameMaze = new Maze(ROWS, COLS);
		player = new Player();
		database = new Database(type);
		dataType = type;
		keyBindings = new KeyBindings();
		
		cheatMode = false;
		cheatDoor = false;
		cheatAnswer = false;
		enteredCheatCode = "";
		
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
		if(stage == null || scene == null || (gameMaze == null && currentScene != SceneType.START))
			return;
		
		stage.setScene(scene);		
		stage.sizeToScene();
		stage.setMinHeight(1080);
		stage.setMinWidth(1920);
		stage.setMaxHeight(1090);
		stage.setMaxWidth(1930);
		stage.setMaximized(true);
		stage.show();
	}
	
	public static void changeScene(SceneType type)
	{
		if (stage == null || (gameMaze == null && type != SceneType.START))
			return;
		
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
						playBgMusic();
					}
					
					if(settingsController != null)
						keyBindings = settingsController.getKeyBindings();

					currentScene = SceneType.MAP;
					setStage(map);
					mapController.update();
					break;

				case CUSTOMIZE:
					if(customize == null)
						customize = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Customize.fxml")));

					currentScene = SceneType.CUSTOMIZE;
					setStage(customize);
					break;

				case SETTINGS:
					if(settings == null)
					{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Main.class.getResource("/application/views/Settings.fxml"));
						Parent root = loader.load();
						settings = new Scene(root);
						settingsController = loader.getController();
					}

					currentScene = SceneType.SETTINGS;
					settingsController.update();
					setStage(settings);
					break;

				case HELP:
					if(help == null)
						help = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Help.fxml")));

					currentScene = SceneType.HELP;
					setStage(help);
					break;

				case WIN:
					if(win == null)
						win = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Win.fxml")));

					currentScene = SceneType.WIN;
					setStage(win);
					break;

				case LOSE:
					if(lose == null)
						lose = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Lose.fxml")));

					currentScene = SceneType.LOSE;
					setStage(lose);
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

					currentScene = SceneType.TRUE_FALSE;
					setStage(trueFalse);
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

					currentScene = SceneType.MULTIPLE_CHOICE;
					setStage(mcQuestions);
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

					currentScene = SceneType.SOUND;
					setStage(soundQuestions);
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

					currentScene = SceneType.VIDEO;
					setStage(videoQuestions);
					break;
					
				case START:
					if(start == null)
						start = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/Start.fxml")));

					currentScene = SceneType.START;
					setStage(start);
					break;
					
				case EDIT_DATABASE:
					if(edit == null)
						edit = new Scene(FXMLLoader.load(Main.class.getResource("/application/views/EditDatabase.fxml")));

					currentScene = SceneType.EDIT_DATABASE;
					setStage(edit);
					break;
			}
			
			if(type != SceneType.VIDEO && type != SceneType.SOUND && bgPlayer != null)
				bgPlayer.play();
			
			else if(bgPlayer != null)
				bgPlayer.pause();
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void checkAnswer(int chosenAnswer)
	{
		if (stage == null || gameMaze == null)
			return;
		
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
	
	public static void cheatModeMove(Direction direction)
	{
		if (stage == null || gameMaze == null)
			return;
		
		if(cheatDoor)
		{
			gameMaze.setDirection(direction);
			gameMaze.updateMazeRooms(player.getPlayerX(), player.getPlayerY(), false);
		}
	}
	
	public static void showQuestion(Direction direction)
	{
		if (stage == null || gameMaze == null || direction == null)
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
				
			case SHORT:
				// TODO
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
	
	public static boolean isCheatMode()
	{
		return cheatMode;
	}
	
	public static boolean isCheatDoor()
	{
		if(cheatMode)
			return cheatDoor;
		
		return false;
	}
	
	public static boolean isCheatAnswer()
	{
		if(cheatMode)
			return cheatAnswer;
		
		return false;
	}
	
	public static void setCheatMode(boolean cheat)
	{
		cheatMode = cheat;
	}
	
	public static void setCheatDoor(boolean cheat)
	{
		cheatDoor = cheat;
	}
	
	public static void setCheatAnswer(boolean cheat)
	{
		cheatAnswer = cheat;
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
		if (stage == null || gameMaze == null)
			return;
		
		else if(status == null)
			throw new IllegalArgumentException("Null Archway Status");
		
		else if(y >= ROWS || y < 0)
			throw new IllegalArgumentException("Invalid y value");
		
		else if(x >= COLS || x < 0)
			throw new IllegalArgumentException("Invalid x value");
		
		horizontalArchways[y][x] = status;
	}
	
	public static void setVerticalArchway(int y, int x, ArchwayStatus status)
	{
		if (stage == null || gameMaze == null)
			return;
		
		else if(status == null)
			throw new IllegalArgumentException("Null Archway Status");
		
		else if(y >= ROWS || y < 0)
			throw new IllegalArgumentException("Invalid y value");
		
		else if(x >= COLS || x < 0)
			throw new IllegalArgumentException("Invalid x value");
		
		verticalArchways[y][x] = status;
	}
	
	public static boolean save()
	{
		if (stage == null || gameMaze == null)
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
		if (stage == null || gameMaze == null)
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
		if (stage == null || gameMaze == null || map == null)
			return;
		
		map.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				String cheatCode = keyBindings.getCheatIdentifier() + "UPUPDOWNDOWNLEFTRIGHTLEFTRIGHTBA";
				enteredCheatCode += event.getCode().toString();

				if(cheatCode.startsWith(enteredCheatCode))
				{
					if(cheatCode.compareTo(enteredCheatCode) == 0)
					{
						cheatMode = true;
						mapController.update();
					}
					return;
				}
				
				else
					enteredCheatCode = "";
				
				if(event.getCode().equals(keyBindings.getCustomize()))
					mapController.customizeBtn();
				
				else if(event.getCode().equals(keyBindings.getSave()))
					mapController.saveBtn();
				
				else if(event.getCode().equals(keyBindings.getLoad()))
					mapController.saveBtn();
				
				else if(event.getCode().equals(keyBindings.getSettings()))
					mapController.settingsBtn();
				
				else if(event.getCode().equals(keyBindings.getHelp()))
					mapController.helpBtn();
				
				else if(event.getCode().equals(keyBindings.getNorth()))
					mapController.nDoorBtn();
				
				else if(event.getCode().equals(keyBindings.getSouth()))
					mapController.sDoorBtn();
				
				else if(event.getCode().equals(keyBindings.getWest()))
					mapController.wDoorBtn();
				
				else if(event.getCode().equals(keyBindings.getEast()))
					mapController.eDoorBtn();
			}
		});
	}
	
	public static void reloadQuestions()
	{
		if (stage == null || gameMaze == null)
			return;
		
		database = new Database(dataType);
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
	
	private static void playBgMusic()
	{
		switch(dataType)
		{
			case Java:
				bgMusic = new Media(Main.class.getResource("/resources/sounds/Kevin MacLeod - Scheming Weasel (faster version).mp3").toExternalForm());
				break;

			case Anime:
				bgMusic = new Media(Main.class.getResource("/resources/sounds/Baccano - Guns & Roses.mp3").toExternalForm());
				break;

			case Video_Games:
				bgMusic = new Media(Main.class.getResource("/resources/sounds/Undertale - Megalovania.mp3").toExternalForm());
				break;
		}
		
		if(bgMusic == null)
			return;
			
		bgPlayer = new MediaPlayer(bgMusic);
		bgPlayer.setVolume(20);
		bgPlayer.setOnEndOfMedia(new Runnable() 
		{
	        @Override
	        public void run() 
	        {
	        	bgPlayer.seek(bgPlayer.getStartTime());
	            bgPlayer.play();
	        }
	    });
	}
	
	public static void setBgVolume(double volume)
	{
		if(bgPlayer == null)
			return;
		
		bgPlayer.setVolume(volume);
	}
	
	public static boolean setBgMuteOrUnmute()
	{
		if(bgPlayer.isMute())
			bgPlayer.setMute(false);
		
		else
			bgPlayer.setMute(true);
		
		return bgPlayer.isMute();
	}
}
