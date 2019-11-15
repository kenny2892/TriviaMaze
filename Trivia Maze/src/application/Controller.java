package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
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

public class Controller
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
	@FXML private Group customizationGroup;
	@FXML private ColorPicker buttonColorPicker;
	@FXML private ColorPicker buttonHighlightColorPicker;
	@FXML private ColorPicker doorColorPicker;
	@FXML private ColorPicker doorHighlightColorPicker;
	@FXML private Group helpGroup;
	@FXML private Group settingsGroup;
	@FXML private ImageView playerIcon;
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
	@FXML private Text winnerText;
	@FXML private ImageView exitIcon;

	private static ImageView[][] horizontalArchWays;
	private static ImageView[][] verticalArchWays;
	private static Maze gameMaze;

	public void initialize()
	{
		mcQuestionText.setWrapText(true);

		gameMaze = new Maze(5, 5);
		setClippingMasks();
		setColors();
		backToMain();
		exitIcon(gameMaze.getExitX(), gameMaze.getExitY());

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
		// Changed player to package
		int playerX = gameMaze.getPlayer().getPlayerX();
		int playerY = gameMaze.getPlayer().getPlayerY();

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
		Room curr = gameMaze.getCurrRoom();
		setDoorStatus(curr, Direction.NORTH, nDoorGroup, nDoorStatusImg);
		setDoorStatus(curr, Direction.SOUTH, sDoorGroup, sDoorStatusImg);
		setDoorStatus(curr, Direction.EAST, eDoorGroup, eDoorStatusImg);
		setDoorStatus(curr, Direction.WEST, wDoorGroup, wDoorStatusImg);
		playerIcon(gameMaze.getPlayer().getPlayerX(), gameMaze.getPlayer().getPlayerY());
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

	private void setColors()
	{
		buttonColorPicker.setValue(Color.web("EE793C"));
		doorColorPicker.setValue(Color.WHITE);

		buttonHighlightColorPicker.setValue(Color.web("F7FE40"));
		doorHighlightColorPicker.setValue(Color.web("F7FE40"));

		buttonColors();
		doorColors();
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
		Color mainColor = buttonColorPicker.getValue();
		Color highlightColor = buttonHighlightColorPicker.getValue();

		changeImageColor(customizeImg, customizeHitBox, mainColor, highlightColor);
		changeImageColor(saveImg, saveHitBox, mainColor, highlightColor);
		changeImageColor(loadImg, loadHitBox, mainColor, highlightColor);
		changeImageColor(settingsImg, settingsHitBox, mainColor, highlightColor);
		changeImageColor(helpImg, helpHitBox, mainColor, highlightColor);
	}

	public void doorColors()
	{
		Color mainColor = doorColorPicker.getValue();
		Color highlightColor = doorHighlightColorPicker.getValue();

		changeImageColor(nDoorImg, nDoorHitBox, mainColor, highlightColor);
		changeImageColor(sDoorImg, sDoorHitBox, mainColor, highlightColor);
		changeImageColor(eDoorImg, eDoorHitBox, mainColor, highlightColor);
		changeImageColor(wDoorImg, wDoorHitBox, mainColor, highlightColor);
	}

	public void customizeBtn()
	{
		System.out.println("Customize");
		backToMain();
		customizationGroup.setVisible(true);
		mapGroup.setVisible(false);
	}

	public void backToMain()
	{
		customizationGroup.setVisible(false);
		helpGroup.setVisible(false);
		settingsGroup.setVisible(false);
		mcGroup.setVisible(false);
		tfGroup.setVisible(false);
		winnerText.setVisible(false);

		mapGroup.setVisible(true);
		showDoors();
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
		System.out.println("Settings");
		backToMain();
		settingsGroup.setVisible(true);
		mapGroup.setVisible(false);
	}

	public void helpBtn()
	{
		System.out.println("Help");
		backToMain();
		helpGroup.setVisible(true);
		mapGroup.setVisible(false);
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
		Room curr = gameMaze.getCurrRoom();
		if (curr.isDoorLocked(direction))
			return;

		else if (curr.isDoorOpened(direction))
		{
			gameMaze.movePlayer(direction);
			backToMain();
			return;
		}

		Question question;
		question = gameMaze.getQuestion(direction);

		if (question instanceof MultipleChoiceQuestion)
			showMcQuestion((MultipleChoiceQuestion) question);

		else if (question instanceof TrueFalseQuestion)
			showTfQuestion((TrueFalseQuestion) question);
	}

	private void playerIcon(int x, int y)
	{
		String index = y + "_" + x;

		switch(index)
		{
			case "0_0":
				playerIcon.setLayoutX(312);
				playerIcon.setLayoutY(83);
				break;

			case "0_1":
				playerIcon.setLayoutX(420);
				playerIcon.setLayoutY(83);
				break;

			case "0_2":
				playerIcon.setLayoutX(530);
				playerIcon.setLayoutY(83);
				break;

			case "0_3":
				playerIcon.setLayoutX(636);
				playerIcon.setLayoutY(83);
				break;

			case "0_4":
				playerIcon.setLayoutX(743);
				playerIcon.setLayoutY(83);
				break;

			case "1_0":
				playerIcon.setLayoutX(302);
				playerIcon.setLayoutY(178);
				break;

			case "1_1":
				playerIcon.setLayoutX(416);
				playerIcon.setLayoutY(178);
				break;

			case "1_2":
				playerIcon.setLayoutX(530);
				playerIcon.setLayoutY(178);
				break;

			case "1_3":
				playerIcon.setLayoutX(643);
				playerIcon.setLayoutY(178);
				break;

			case "1_4":
				playerIcon.setLayoutX(754);
				playerIcon.setLayoutY(178);
				break;

			case "2_0":
				playerIcon.setLayoutX(291);
				playerIcon.setLayoutY(280);
				break;

			case "2_1":
				playerIcon.setLayoutX(408);
				playerIcon.setLayoutY(280);
				break;

			case "2_2":
				playerIcon.setLayoutX(530);
				playerIcon.setLayoutY(280);
				break;

			case "2_3":
				playerIcon.setLayoutX(649);
				playerIcon.setLayoutY(280);
				break;

			case "2_4":
				playerIcon.setLayoutX(768);
				playerIcon.setLayoutY(280);
				break;

			case "3_0":
				playerIcon.setLayoutX(279);
				playerIcon.setLayoutY(390);
				break;

			case "3_1":
				playerIcon.setLayoutX(404);
				playerIcon.setLayoutY(390);
				break;

			case "3_2":
				playerIcon.setLayoutX(530);
				playerIcon.setLayoutY(390);
				break;

			case "3_3":
				playerIcon.setLayoutX(656);
				playerIcon.setLayoutY(390);
				break;

			case "3_4":
				playerIcon.setLayoutX(783);
				playerIcon.setLayoutY(390);
				break;

			case "4_0":
				playerIcon.setLayoutX(265);
				playerIcon.setLayoutY(486);
				break;

			case "4_1":
				playerIcon.setLayoutX(400);
				playerIcon.setLayoutY(486);
				break;

			case "4_2":
				playerIcon.setLayoutX(530);
				playerIcon.setLayoutY(486);
				break;

			case "4_3":
				playerIcon.setLayoutX(666);
				playerIcon.setLayoutY(486);
				break;

			case "4_4":
				playerIcon.setLayoutX(795);
				playerIcon.setLayoutY(486);
				break;
		}
	}

	private void exitIcon(int x, int y)
	{
		String index = y + "_" + x;

		switch(index)
		{
			case "0_0":
				exitIcon.setLayoutX(315);
				exitIcon.setLayoutY(173);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "0_1":
				exitIcon.setLayoutX(426);
				exitIcon.setLayoutY(173);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "0_2":
				exitIcon.setLayoutX(534);
				exitIcon.setLayoutY(173);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "0_3":
				exitIcon.setLayoutX(640);
				exitIcon.setLayoutY(173);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "0_4":
				exitIcon.setLayoutX(748);
				exitIcon.setLayoutY(173);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "1_0":
				exitIcon.setLayoutX(305);
				exitIcon.setLayoutY(262);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "1_1":
				exitIcon.setLayoutX(419);
				exitIcon.setLayoutY(262);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "1_2":
				exitIcon.setLayoutX(534);
				exitIcon.setLayoutY(262);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "1_3":
				exitIcon.setLayoutX(646);
				exitIcon.setLayoutY(262);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "1_4":
				exitIcon.setLayoutX(754);
				exitIcon.setLayoutY(262);
				exitIcon.setFitWidth(74);
				exitIcon.setFitHeight(73);
				break;

			case "2_0":
				exitIcon.setLayoutX(293);
				exitIcon.setLayoutY(356);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "2_1":
				exitIcon.setLayoutX(408);
				exitIcon.setLayoutY(356);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "2_2":
				exitIcon.setLayoutX(530);
				exitIcon.setLayoutY(356);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "2_3":
				exitIcon.setLayoutX(649);
				exitIcon.setLayoutY(356);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "2_4":
				exitIcon.setLayoutX(768);
				exitIcon.setLayoutY(356);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "3_0":
				exitIcon.setLayoutX(278);
				exitIcon.setLayoutY(464);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "3_1":
				exitIcon.setLayoutX(404);
				exitIcon.setLayoutY(464);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "3_2":
				exitIcon.setLayoutX(530);
				exitIcon.setLayoutY(464);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "3_3":
				exitIcon.setLayoutX(656);
				exitIcon.setLayoutY(464);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "3_4":
				exitIcon.setLayoutX(781);
				exitIcon.setLayoutY(464);
				exitIcon.setFitWidth(85);
				exitIcon.setFitHeight(79);
				break;

			case "4_0":
				exitIcon.setLayoutX(252);
				exitIcon.setLayoutY(572);
				exitIcon.setFitWidth(126);
				exitIcon.setFitHeight(103);
				break;

			case "4_1":
				exitIcon.setLayoutX(385);
				exitIcon.setLayoutY(572);
				exitIcon.setFitWidth(126);
				exitIcon.setFitHeight(103);
				break;

			case "4_2":
				exitIcon.setLayoutX(518);
				exitIcon.setLayoutY(572);
				exitIcon.setFitWidth(126);
				exitIcon.setFitHeight(103);
				break;

			case "4_3":
				exitIcon.setLayoutX(652);
				exitIcon.setLayoutY(572);
				exitIcon.setFitWidth(126);
				exitIcon.setFitHeight(103);
				break;

			case "4_4":
				exitIcon.setLayoutX(785);
				exitIcon.setLayoutY(572);
				exitIcon.setFitWidth(126);
				exitIcon.setFitHeight(103);
				break;
		}
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
		if (gameMaze.checkAnswer(answerIndex))
		{
			setArchwayToCheckMark(getArchway(gameMaze.getCurrentDirection()));
			if(gameMaze.movePlayer(gameMaze.getCurrentDirection()))
			{
				backToMain();
				mapGroup.setVisible(false);
				winnerText.setVisible(true);
				return;
			}
		}
		
		else
			setArchwayToXMark(getArchway(gameMaze.getCurrentDirection()));

		backToMain();
	}
}
