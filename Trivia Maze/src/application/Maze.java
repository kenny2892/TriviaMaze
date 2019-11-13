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
	private static int playerX;
	private static int playerY;
	
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
		gameMaze = createMaze(mazeRows, mazeColumns);
		
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
	
	public static int getPlayerX()
	{
		return playerX;
	}
	
	public static int getPlayerY()
	{
		return playerY;
	}
}
