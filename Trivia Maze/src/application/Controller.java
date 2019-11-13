package application;

import java.io.File;
import java.net.URL;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

	public void initialize()
	{
		setClippingMasks();
		setColors();
		backToMain();
	}

	private void setClippingMasks()
	{
		File customizeFile = new File(this.getClass().getResource("/resources/images/Customize.png").getPath().replace("%20", " ").substring(1));

		ImageView clip = new ImageView(new Image(customizeFile.toURI().toString()));
		customizeImg.setClip(clip);

		File saveFile = new File(this.getClass().getResource("/resources/images/Save.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(saveFile.toURI().toString()));
		saveImg.setClip(clip);

		File loadFile = new File(this.getClass().getResource("/resources/images/Load.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(loadFile.toURI().toString()));
		loadImg.setClip(clip);

		File settingFile = new File(this.getClass().getResource("/resources/images/Settings.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(settingFile.toURI().toString()));
		settingsImg.setClip(clip);

		File helpFile = new File(this.getClass().getResource("/resources/images/Help.png").getPath().replace("%20", " ").substring(1));
		clip = new ImageView(new Image(helpFile.toURI().toString()));
		helpImg.setClip(clip);

		nDoorImg.setClip(clipDoor());
		sDoorImg.setClip(clipDoor());
		eDoorImg.setClip(clipDoor());
		wDoorImg.setClip(clipDoor());
	}

	private ImageView clipDoor()
	{
		File doorFile = new File(this.getClass().getResource("/resources/images/Door.png").getPath().replace("%20", " ").substring(1));
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
		customizationGroup.setVisible(true);
		mapGroup.setVisible(false);
	}
	
	public void backToMain()
	{
		customizationGroup.setVisible(false);
		helpGroup.setVisible(false);
		settingsGroup.setVisible(false);
		
		mapGroup.setVisible(true);
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
		settingsGroup.setVisible(true);
		mapGroup.setVisible(false);
	}

	public void helpBtn()
	{
		System.out.println("Help");
		helpGroup.setVisible(true);
		mapGroup.setVisible(false);
	}

	public void nDoorBtn()
	{
		System.out.println("North Door");
		setDoorStatus(Direction.NORTH, true);
	}

	public void sDoorBtn()
	{
		System.out.println("South Door");
		setDoorStatus(Direction.SOUTH, false);
	}

	public void eDoorBtn()
	{
		System.out.println("East Door");
		setDoorStatus(Direction.EAST, false);
	}

	public void wDoorBtn()
	{
		System.out.println("West Door");
		setDoorStatus(Direction.WEST, true);
	}
	
	private void setDoorStatus(Direction direction, boolean isLocked)
	{
		switch(direction)
		{
			case NORTH:
				doorStatusDisplay(nDoorImg, isLocked);
				break;

			case EAST:
				doorStatusDisplay(eDoorImg, isLocked);
				break;

			case WEST:
				doorStatusDisplay(wDoorImg, isLocked);
				break;

			case SOUTH:
				doorStatusDisplay(sDoorImg, isLocked);
				break;
		}
	}
	
	private void doorStatusDisplay(ImageView view, boolean isLocked)
	{
		URL imgURL = null;
		
		if(isLocked)
			imgURL = this.getClass().getResource("/resources/images/Door X.png");
		
		else 
			imgURL = this.getClass().getResource("/resources/images/Door Check.png");
			
		String path = imgURL.toExternalForm();
		Image pic = new Image(path);
		
		view.setImage(pic);
	}
}
