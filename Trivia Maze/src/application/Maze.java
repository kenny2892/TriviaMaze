package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Maze extends Application
{
	private int playerX;
	private int playerY;
	
	private static Room[][] gameMaze;
	
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
		mazeBuilder(mazeRows, mazeColumns);
		
		launch(args);
	}
	
	private static void mazeBuilder(final int mazeRows, final int mazeColumns)
	{
		gameMaze = new Room[mazeRows][mazeColumns];
		
		setUpInsideMaze(mazeRows, mazeColumns);
		setUpMazeBorder(mazeRows, mazeColumns);
		setUpMazeCorners(mazeRows, mazeColumns);
	}
	
	private static void setUpInsideMaze(final int mazeRows, final int mazeColumns)
	{
		boolean doorIsLocked = false;
		Question defaultQuestion = new NullQuestion();
		Door genericDoor = new Door(doorIsLocked, defaultQuestion);
		
		for(int rowIndex = 1; rowIndex < mazeRows - 1; rowIndex++)
		{
			for(int columnIndex = 1; columnIndex < mazeColumns - 1; columnIndex++)
			{
				Door northDoor = genericDoor;
				Door southDoor = genericDoor;
				Door eastDoor = genericDoor;
				Door westDoor = genericDoor;
				
				gameMaze[rowIndex][columnIndex] = new Room
						(
						 northDoor,
						 southDoor,
						 eastDoor,
						 westDoor
						 );
			}
		}
	}
	
	private static void setUpMazeBorder(final int mazeRows, final int mazeColumns)
	{
		boolean doorIsLocked = false;
		Question defaultQuestion = new NullQuestion();
		Door genericDoor = new Door(doorIsLocked, defaultQuestion);
		
		for(int columnIndex = 0; columnIndex < mazeColumns - 1; columnIndex++)
		{
			// Top
			Door northDoor = new NullDoor();
			Door southDoor = genericDoor;
			Door eastDoor = genericDoor;
			Door westDoor = genericDoor;
			
			gameMaze[0][columnIndex] = new Room
					(
					northDoor,
					southDoor,
					eastDoor,
					westDoor
					);
			
			// Bottom
			northDoor = genericDoor;
			southDoor = new NullDoor();
			eastDoor = genericDoor;
			westDoor = genericDoor;
			
			gameMaze[mazeRows][columnIndex] = new Room
					(
					northDoor,
					southDoor,
					eastDoor,
					westDoor
					);
		}
		
		for(int rowIndex = 0; rowIndex < mazeColumns - 1; rowIndex++)
		{
			// Left
			Door northDoor = genericDoor;
			Door southDoor = genericDoor;
			Door eastDoor = genericDoor;
			Door westDoor = new NullDoor();
			
			gameMaze[rowIndex][0] = new Room
					(
					northDoor,
					southDoor,
					eastDoor,
					westDoor
					);
			
			// Right
			northDoor = genericDoor;
			southDoor = new NullDoor();
			eastDoor = genericDoor;
			westDoor = genericDoor;
			
			gameMaze[rowIndex][mazeColumns] = new Room
					(
					northDoor,
					southDoor,
					eastDoor,
					westDoor
					);
		}
		
	}
	
	private static void setUpMazeCorners(final int mazeRows, final int mazeColumns)
	{
		boolean doorIsLocked = false;
		Question defaultQuestion = new NullQuestion();
		Door genericDoor = new Door(doorIsLocked, defaultQuestion);
		
		// Top Left
		Door northDoor = new NullDoor();
		Door southDoor = genericDoor;
		Door eastDoor = genericDoor;
		Door westDoor = new NullDoor();
		
		gameMaze[0][0] = new Room
				(
				northDoor,
				southDoor,
				eastDoor,
				westDoor
				);
		
		// Top Right
		northDoor = new NullDoor();
		southDoor = genericDoor;
		eastDoor = new NullDoor();
		westDoor = genericDoor;
		
		gameMaze[0][mazeColumns] = new Room
				(
				northDoor,
				southDoor,
				eastDoor,
				westDoor
				);
		
		// Bottom Right
		northDoor = genericDoor;
		southDoor = new NullDoor();
		eastDoor = new NullDoor();
		westDoor = genericDoor;
		
		gameMaze[mazeRows][mazeColumns] = new Room
				(
				northDoor,
				southDoor,
				eastDoor,
				westDoor
				);
		
		// Bottom Left
		northDoor = genericDoor;
		southDoor = new NullDoor();
		eastDoor = genericDoor;
		westDoor = new NullDoor();
		
		gameMaze[mazeRows][0] = new Room
				(
				northDoor,
				southDoor,
				eastDoor,
				westDoor
				);
	}
}
