package application.controllers;

import java.io.File;
import java.net.URL;

import application.Direction;
import application.Main;
import application.Maze;
import application.Player;
import application.Room;
import application.SceneType;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapController
{
	@FXML private ImageView customizeImg;
	@FXML private ImageView saveImg;
	@FXML private ImageView loadImg;
	@FXML private ImageView settingsImg;
	@FXML private ImageView helpImg;
	@FXML private Rectangle customizeHitBox;
	@FXML private Rectangle saveHitBox;
	@FXML private Rectangle loadHitBox;
	@FXML private Rectangle settingsHitBox;
	@FXML private Rectangle helpHitBox;
	@FXML private Group doorStatusGroup;
	@FXML private Group mapGroup;
	@FXML private Group nDoorGroup;
	@FXML private Group sDoorGroup;
	@FXML private Group eDoorGroup;
	@FXML private Group wDoorGroup;
	@FXML private ImageView nDoorImg;
	@FXML private ImageView sDoorImg;
	@FXML private ImageView wDoorImg;
	@FXML private ImageView eDoorImg;
	@FXML private ImageView nDoorStatusImg;
	@FXML private ImageView sDoorStatusImg;
	@FXML private ImageView wDoorStatusImg;
	@FXML private ImageView eDoorStatusImg;
	@FXML private Rectangle nDoorHitBox;
	@FXML private Rectangle sDoorHitBox;
	@FXML private Rectangle wDoorHitBox;
	@FXML private Rectangle eDoorHitBox;
	@FXML private ImageView playerIcon;
	@FXML private ImageView exitIcon;
	
	private static double[][][] horizontalArchWays;
	private static double[][][] verticalArchWays;

	public void initialize()
	{
		setClippingMasks();

		mapGroup.setVisible(true);
		showDoors();
		
		exitIcon(Main.getMaze().getExitX(), Main.getMaze().getExitY());

		placeArchWays();
	}

	private void placeArchWays()
	{
		horizontalArchWays = new double[][][]
		{
				{ {96, 0}, {206, 0}, {314, 0}, {424, 0} },
				{ {96, 102}, {206, 102}, {314, 102}, {434, 102} },
				{ {81, 197}, {206, 197}, {314, 197}, {448, 197} },
				{ {71, 303}, {196, 303}, {321, 303}, {445, 303} },
				{ {59, 425}, {192, 425}, {327, 425}, {460, 425} } };

		verticalArchWays = new double[][][]
		{
				{ {40, 56}, {150, 56}, {261, 56}, {376, 56}, {485, 56} },
				{ {28, 147}, {147, 147}, {261, 147}, {379, 147}, {501, 147} },
				{ {16, 249}, {140, 249}, {261, 249}, {388, 249}, {515, 249} },
				{ {0, 363}, {134, 363}, {261, 363}, {400, 363}, {529, 363} } };
	}

	private void setArchway(boolean isLocked, Direction direction)
	{
		ImageView doorway = new ImageView();
		
		if(isLocked)
			doorway.setImage(new Image(this.getClass().getResource("/resources/images/X.png").toExternalForm()));
		
		else
			doorway.setImage(new Image(this.getClass().getResource("/resources/images/Checkmark.png").toExternalForm()));

		doorway.setFitWidth(66);
		doorway.setFitHeight(54);
		
		int x = Main.getPlayer().getPlayerX();
		int y = Main.getPlayer().getPlayerY();
		double[] coords = new double[] { 0, 0 };
		
		switch(direction)
		{
			case NORTH:
				if((y - 1) >= 0 && (y < 5))
					coords = verticalArchWays[y - 1][x];
				
				break;
				
			case SOUTH:
				if((y + 1) >= 0 && (y < 5))
					coords = verticalArchWays[y][x];
				
				break;

			case EAST:
				if((x + 1) >= 0 && (x < 5))
					coords = horizontalArchWays[y][x];
				
				break;

			case WEST:
				if((x - 1) >= 0 && (x < 5))
					coords = horizontalArchWays[y][x - 1];
				
				break;
				
			default:
				return;
		}
		
		doorway.setLayoutX(coords[0]);
		doorway.setLayoutY(coords[1]);
		
		doorStatusGroup.getChildren().add(doorway);
	}

	private void showDoors()
	{
		Maze maze = Main.getMaze();
		Player player = Main.getPlayer();

		Room curr = maze.getRoom(player.getPlayerX(), player.getPlayerY());
		setDoorStatus(curr, Direction.NORTH, nDoorGroup, nDoorStatusImg);
		setDoorStatus(curr, Direction.SOUTH, sDoorGroup, sDoorStatusImg);
		setDoorStatus(curr, Direction.EAST, eDoorGroup, eDoorStatusImg);
		setDoorStatus(curr, Direction.WEST, wDoorGroup, wDoorStatusImg);
		playerIcon(player.getPlayerX(), player.getPlayerY());
	}

	private void setDoorStatus(Room curr, Direction direction, Group doorGroup, ImageView statusImg)
	{
		if (curr.isDoorLocked(direction) && !curr.isDoorOpened(direction))
		{
			doorGroup.setVisible(false);
			return;
		}

		doorGroup.setVisible(true);

		if (curr.isDoorOpened(direction))
		{
			URL imgURL = null;

			if (curr.isDoorLocked(direction))
				imgURL = this.getClass().getResource("/resources/images/Door X.png");

			else
				imgURL = this.getClass().getResource("/resources/images/Door Check.png");

			String path = imgURL.toExternalForm();
			Image pic = new Image(path);

			statusImg.setImage(pic);
		}

		else
			statusImg.setImage(null);
	}

	private void setClippingMasks()
	{
		File customizeFile = new File(
				this.getClass().getResource("/resources/images/Customize.png").getPath().replace("%20", " ").substring(1));

		ImageView clip = new ImageView(new Image(customizeFile.toURI().toString()));
		customizeImg.setClip(clip);

		File saveFile = new File(
				this.getClass().getResource("/resources/images/Save.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(saveFile.toURI().toString()));
		saveImg.setClip(clip);

		File loadFile = new File(
				this.getClass().getResource("/resources/images/Load.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(loadFile.toURI().toString()));
		loadImg.setClip(clip);

		File settingFile = new File(
				this.getClass().getResource("/resources/images/Settings.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(settingFile.toURI().toString()));
		settingsImg.setClip(clip);

		File helpFile = new File(
				this.getClass().getResource("/resources/images/Help.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(helpFile.toURI().toString()));
		helpImg.setClip(clip);

		nDoorImg.setClip(clipDoor());
		sDoorImg.setClip(clipDoor());
		eDoorImg.setClip(clipDoor());
		wDoorImg.setClip(clipDoor());
	}

	private ImageView clipDoor()
	{
		File doorFile = new File(
				this.getClass().getResource("/resources/images/Door.png").getPath().replace("%20", " ").substring(1));
		return new ImageView(new Image(doorFile.toURI().toString()));
	}
	
	public void update()
	{
		buttonColors();
		doorColors();
		arrowType();
	}

	private void changeImageColor(ImageView imageToChange, Rectangle hitBox, Color mainColor, Color highlightColor)
	{
		ColorAdjust adjust = new ColorAdjust();

		adjust.setBrightness(mainColor.getBrightness());
		adjust.setHue(mainColor.getHue());
		adjust.setSaturation(mainColor.getSaturation());
		adjust.setContrast(1);

		ColorAdjust monochrome = new ColorAdjust();
		monochrome.setSaturation(-1.0);

		Blend highlight = new Blend(BlendMode.MULTIPLY, monochrome,
				new ColorInput(0, 0, imageToChange.getImage().getWidth(), imageToChange.getImage().getHeight(), highlightColor));

		Blend colorize = new Blend(BlendMode.MULTIPLY, adjust,
				new ColorInput(0, 0, imageToChange.getImage().getWidth(), imageToChange.getImage().getHeight(), mainColor));

		imageToChange.effectProperty()
				.bind(Bindings.when(hitBox.hoverProperty()).then((Effect) highlight).otherwise((Effect) colorize));

		imageToChange.setCache(true);
		imageToChange.setCacheHint(CacheHint.SPEED);
	}

	public void buttonColors()
	{
		Color btnColor = CustomizeController.getBtnColor();
		Color btnHighlightColor = CustomizeController.getBtnHighlightColor();

		changeImageColor(customizeImg, customizeHitBox, btnColor, btnHighlightColor);
		changeImageColor(saveImg, saveHitBox, btnColor, btnHighlightColor);
		changeImageColor(loadImg, loadHitBox, btnColor, btnHighlightColor);
		changeImageColor(settingsImg, settingsHitBox, btnColor, btnHighlightColor);
		changeImageColor(helpImg, helpHitBox, btnColor, btnHighlightColor);
	}

	public void doorColors()
	{
		Color doorColor = CustomizeController.getDoorColor();
		Color doorHighlightColor = CustomizeController.getDoorHighlightColor();

		changeImageColor(nDoorImg, nDoorHitBox, doorColor, doorHighlightColor);
		changeImageColor(sDoorImg, sDoorHitBox, doorColor, doorHighlightColor);
		changeImageColor(eDoorImg, eDoorHitBox, doorColor, doorHighlightColor);
		changeImageColor(wDoorImg, wDoorHitBox, doorColor, doorHighlightColor);
	}
	
	private void arrowType()
	{
		Image type = CustomizeController.getArrowType();
		
		if(type != null)
			playerIcon.setImage(type);
	}

	public void customizeBtn()
	{
		Main.changeScene(SceneType.CUSTOMIZE);
	}

	public void saveBtn()
	{
		System.out.println("Save");
	}

	public void loadBtn()
	{
		System.out.println("Load");
	}

	public void settingsBtn()
	{
		Main.changeScene(SceneType.SETTINGS);
	}

	public void helpBtn()
	{
		Main.changeScene(SceneType.HELP);
	}

	public void nDoorBtn()
	{
		doorBtn(Direction.NORTH);
	}

	public void sDoorBtn()
	{
		doorBtn(Direction.SOUTH);
	}

	public void eDoorBtn()
	{
		doorBtn(Direction.EAST);
	}

	public void wDoorBtn()
	{
		doorBtn(Direction.WEST);
	}

	private void doorBtn(Direction direction)
	{
		Maze maze = Main.getMaze();
		Player player = Main.getPlayer();

		Room curr = maze.getRoom(player.getPlayerX(), player.getPlayerY());
		if (curr.isDoorLocked(direction))
			return;

		else if (curr.isDoorOpened(direction))
		{
			player.movePlayer(maze, direction);
			showDoors();
			return;
		}

		Main.showQuestion(direction);
	}

	private void playerIcon(int x, int y)
	{
		int[] yAxis = new int[]
		{ 83, 178, 280, 390, 486 };

		int[][] xAxis = new int[][]
		{
				{ 312, 420, 530, 636, 743 },
				{ 302, 416, 530, 643, 754 },
				{ 291, 408, 530, 649, 768 },
				{ 279, 404, 530, 656, 783 },
				{ 265, 400, 530, 666, 795 } };

		playerIcon.setLayoutX(xAxis[y][x]);
		playerIcon.setLayoutY(yAxis[y]);
	}

	private void exitIcon(int x, int y)
	{
		int[] width = new int[]
		{ 74, 74, 85, 85, 126 };

		int[] height = new int[]
		{ 73, 73, 79, 79, 103 };
		
		int[] yAxis = new int[]
		{ 173, 262, 356, 464, 572 };

		int[][] xAxis = new int[][]
		{
				{ 315, 426, 534, 640, 748 },
				{ 305, 408, 534, 646, 754 },
				{ 293, 408, 530, 649, 768 },
				{ 278, 404, 530, 656, 781 },
				{ 252, 385, 518, 652, 785 } };
				
		exitIcon.setLayoutX(xAxis[y][x]);
		exitIcon.setLayoutY(yAxis[y]);
		exitIcon.setFitWidth(width[y]);
		exitIcon.setFitHeight(height[y]);
	}
	
	public void updateMaze(boolean correct)
	{
		Maze maze = Main.getMaze();
		Player player = Main.getPlayer();
		
		if(correct)
		{
			setArchway(false, maze.getCurrentDirection());
			if (player.movePlayer(maze, maze.getCurrentDirection()))
			{
				showDoors();
				Main.changeScene(SceneType.WIN);
			}
		}
		
		else
			setArchway(true, maze.getCurrentDirection());
		
		showDoors();
	}
}
