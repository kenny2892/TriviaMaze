package application.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import application.Direction;
import application.Main;
import application.Maze;
import application.MultipleChoiceQuestion;
import application.Player;
import application.Question;
import application.Room;
import application.SceneType;
import application.TrueFalseQuestion;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MapAndQuestionsController
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
	
	@FXML private ImageView doorway1;
	@FXML private ImageView doorway2;
	@FXML private ImageView doorway3;
	@FXML private ImageView doorway4;
	@FXML private ImageView doorway5;
	@FXML private ImageView doorway6;
	@FXML private ImageView doorway7;
	@FXML private ImageView doorway8;
	@FXML private ImageView doorway9;
	@FXML private ImageView doorway10;
	@FXML private ImageView doorway11;
	@FXML private ImageView doorway12;
	@FXML private ImageView doorway13;
	@FXML private ImageView doorway14;
	@FXML private ImageView doorway15;
	@FXML private ImageView doorway16;
	@FXML private ImageView doorway17;
	@FXML private ImageView doorway18;
	@FXML private ImageView doorway19;
	@FXML private ImageView doorway20;
	@FXML private ImageView doorway21;
	@FXML private ImageView doorway22;
	@FXML private ImageView doorway23;
	@FXML private ImageView doorway24;
	@FXML private ImageView doorway25;
	@FXML private ImageView doorway26;
	@FXML private ImageView doorway27;
	@FXML private ImageView doorway28;
	@FXML private ImageView doorway29;
	@FXML private ImageView doorway30;
	@FXML private ImageView doorway31;
	@FXML private ImageView doorway32;
	@FXML private ImageView doorway33;
	@FXML private ImageView doorway34;
	@FXML private ImageView doorway35;
	@FXML private ImageView doorway36;
	@FXML private ImageView doorway37;
	@FXML private ImageView doorway38;
	@FXML private ImageView doorway39;
	@FXML private ImageView doorway40;

	@FXML private Group mcGroup;
	@FXML private TextArea mcQuestionText;
	@FXML private Group mcGroupA;
	@FXML private Text mcTextA;
	@FXML private Group mcGroupB;
	@FXML private Text mcTextB;
	@FXML private Group mcGroupC;
	@FXML private Text mcTextC;
	@FXML private Group mcGroupD;
	@FXML private Text mcTextD;
	@FXML private Group mcGroupE;
	@FXML private Text mcTextE;
	@FXML private Group mcGroupF;
	@FXML private Text mcTextF;
	@FXML private Group tfGroup;
	@FXML private TextArea tfQuestionText;
	
	private static ImageView[][] horizontalArchWays;
	private static ImageView[][] verticalArchWays;

	public void initialize()
	{
		setClippingMasks();
		buttonColors();
		doorColors();
		arrowType();

		mapGroup.setVisible(true);
		showDoors();
		
		exitIcon(Main.getMaze().getExitX(), Main.getMaze().getExitY());

		placeArchWays();
	}

	private void placeArchWays()
	{
		horizontalArchWays = new ImageView[][]
		{
				{ doorway1, doorway2, doorway3, doorway4 },
				{ doorway10, doorway11, doorway12, doorway13 },
				{ doorway19, doorway20, doorway21, doorway22 },
				{ doorway28, doorway29, doorway30, doorway31 },
				{ doorway37, doorway38, doorway39, doorway40 } };

		verticalArchWays = new ImageView[][]
		{
				{ doorway5, doorway6, doorway7, doorway8, doorway9 },
				{ doorway14, doorway15, doorway16, doorway17, doorway18 },
				{ doorway23, doorway24, doorway25, doorway26, doorway27 },
				{ doorway32, doorway33, doorway34, doorway35, doorway36 } };
	}

	private ImageView getArchway(Direction direction)
	{
		int playerX = Main.getPlayer().getPlayerX();
		int playerY = Main.getPlayer().getPlayerY();

		switch(direction)
		{
			case NORTH:
				return (playerY - 1) >= 0 && (playerY < 5) ? verticalArchWays[playerY - 1][playerX] : null;
			case SOUTH:
				return (playerY + 1) >= 0 && (playerY < 5) ? verticalArchWays[playerY][playerX] : null;
			case EAST:
				return (playerX + 1) >= 0 && (playerX < 5) ? horizontalArchWays[playerY][playerX] : null;
			case WEST:
				return (playerX - 1) >= 0 && (playerX < 5) ? horizontalArchWays[playerY][playerX - 1] : null;
			default:
				return null;
		}
	}

	private void setArchwayToCheckMark(ImageView archway)
	{
		archway.setImage(new Image(this.getClass().getResource("/resources/images/Checkmark.png").toExternalForm()));
	}

	private void setArchwayToXMark(ImageView archway)
	{
		archway.setImage(new Image(this.getClass().getResource("/resources/images/X.png").toExternalForm()));
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
		Color mainColor = CustomizeController.getBtnColor();
		Color highlightColor = CustomizeController.getBtnHighlightColor();

		changeImageColor(customizeImg, customizeHitBox, mainColor, highlightColor);
		changeImageColor(saveImg, saveHitBox, mainColor, highlightColor);
		changeImageColor(loadImg, loadHitBox, mainColor, highlightColor);
		changeImageColor(settingsImg, settingsHitBox, mainColor, highlightColor);
		changeImageColor(helpImg, helpHitBox, mainColor, highlightColor);
	}

	public void doorColors()
	{
		Color mainColor = CustomizeController.getDoorColor();
		Color highlightColor = CustomizeController.getDoorHighlightColor();

		changeImageColor(nDoorImg, nDoorHitBox, mainColor, highlightColor);
		changeImageColor(sDoorImg, sDoorHitBox, mainColor, highlightColor);
		changeImageColor(eDoorImg, eDoorHitBox, mainColor, highlightColor);
		changeImageColor(wDoorImg, wDoorHitBox, mainColor, highlightColor);
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

		Question question;
		question = Main.getQuestion(direction);

		if (question instanceof MultipleChoiceQuestion)
			showMcQuestion((MultipleChoiceQuestion) question);

		else if (question instanceof TrueFalseQuestion)
			showTfQuestion((TrueFalseQuestion) question);
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

	public void backToMain()
	{
		mcGroup.setVisible(false);
		tfGroup.setVisible(false);

		mapGroup.setVisible(true);
		showDoors();
	}

	private void showMcQuestion(MultipleChoiceQuestion question)
	{
		mapGroup.setVisible(false);
		mcGroup.setVisible(true);
		mcQuestionText.setText(question.getQuestion());
		ArrayList<String> answers = question.getAnswers();

		hideAllAnswers();

		switch(answers.size())
		{
			case 6:
				mcGroupF.setVisible(true);
				mcTextF.setText(answers.get(5));

			case 5:
				mcGroupE.setVisible(true);
				mcTextE.setText(answers.get(4));

			case 4:
				mcGroupD.setVisible(true);
				mcTextD.setText(answers.get(3));

			case 3:
				mcGroupC.setVisible(true);
				mcTextC.setText(answers.get(2));

			case 2:
				mcGroupB.setVisible(true);
				mcTextB.setText(answers.get(1));

			case 1:
				mcGroupA.setVisible(true);
				mcTextA.setText(answers.get(0));
		}
	}

	private void showTfQuestion(TrueFalseQuestion question)
	{
		mapGroup.setVisible(false);
		tfGroup.setVisible(true);

		tfQuestionText.setText(question.getQuestion());
	}

	private void hideAllAnswers()
	{
		mcGroupA.setVisible(false);
		mcGroupB.setVisible(false);
		mcGroupC.setVisible(false);
		mcGroupD.setVisible(false);
		mcGroupE.setVisible(false);
		mcGroupF.setVisible(false);
	}

	public void selectmcA()
	{
		selectAnswer(0);
	}

	public void selectmcB()
	{
		selectAnswer(1);
	}

	public void selectmcC()
	{
		selectAnswer(2);
	}

	public void selectmcD()
	{
		selectAnswer(3);
	}

	public void selectmcE()
	{
		selectAnswer(4);
	}

	public void selectmcF()
	{
		selectAnswer(5);
	}

	public void selectTrue()
	{
		selectAnswer(0);
	}

	public void selectFalse()
	{
		selectAnswer(1);
	}

	private void selectAnswer(int answerIndex)
	{
		Maze maze = Main.getMaze();
		Player player = Main.getPlayer();

		if (Main.checkAnswer(answerIndex))
		{
			setArchwayToCheckMark(getArchway(maze.getCurrentDirection()));
			if (player.movePlayer(maze, maze.getCurrentDirection()))
			{
				backToMain();
				Main.changeScene(SceneType.WIN);
			}
		}

		else
			setArchwayToXMark(getArchway(maze.getCurrentDirection()));

		backToMain();
	}
}
