package application.controllers;

import java.io.File;
import java.net.URL;

import application.Main;
import application.enums.SceneType;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomizeController
{
	@FXML private ColorPicker buttonColorPicker;
	@FXML private ColorPicker buttonHighlightColorPicker;
	@FXML private ColorPicker doorColorPicker;
	@FXML private ColorPicker doorHighlightColorPicker;
	@FXML private Spinner<Integer> arrowTypeSpinner;
	@FXML private ImageView arrowImg;
	@FXML private ImageView btnImg;
	@FXML private Rectangle btnHitBox;
	@FXML private ImageView doorImg;
	@FXML private Rectangle doorHitBox;
	
	private static Color btnColor = Color.web("EE793C");
	private static Color doorColor = Color.WHITE;
	private static Color btnHighlightColor = Color.web("F7FE40");
	private static Color doorHighlightColor = Color.web("F7FE40");
	private static int arrowType = 1;
	
	public void initialize()
	{
		setColor();
		setClippingMasks();
		arrowTypeSpinner.getValueFactory().setValue(arrowType);
		setArrow(arrowType);
	}
	
	private void setColor()
	{
		buttonColorPicker.setValue(btnColor);
		doorColorPicker.setValue(doorColor);

		buttonHighlightColorPicker.setValue(btnHighlightColor);
		doorHighlightColorPicker.setValue(doorHighlightColor);
		
		buttonColors();
		doorColors();
	}
	
	private void setClippingMasks()
	{
		File previewFile = new File(
				this.getClass().getResource("/resources/images/Preview.png").getPath().replace("%20", " ").substring(1));

		ImageView clip = new ImageView(new Image(previewFile.toURI().toString()));
		btnImg.setClip(clip);
		
		File doorFile = new File(
				this.getClass().getResource("/resources/images/Door.png").getPath().replace("%20", " ").substring(1));
		
		clip = new ImageView(new Image(doorFile.toURI().toString()));
		doorImg.setClip(clip);
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

		changeImageColor(btnImg, btnHitBox, mainColor, highlightColor);
		
		btnColor = mainColor;
		btnHighlightColor = highlightColor;
		playBtnSound();
	}

	public void doorColors()
	{
		Color mainColor = doorColorPicker.getValue();
		Color highlightColor = doorHighlightColorPicker.getValue();

		changeImageColor(doorImg, doorHitBox, mainColor, highlightColor);
		
		doorColor = mainColor;
		doorHighlightColor = highlightColor;
		playBtnSound();
	}
	
	public void arrowType()
	{
		int index = arrowTypeSpinner.getValueFactory().getValue();
		setArrow(index);
		playBtnSound();
	}
	
	private void setArrow(int index)
	{
		URL imgURL = null;
		
		switch(index)
		{
			case 1:
				imgURL = this.getClass().getResource("/resources/images/Player Arrow - A.png");
				break;

			case 2:
				imgURL = this.getClass().getResource("/resources/images/Player Arrow - B.png");
				break;

			case 3:
				imgURL = this.getClass().getResource("/resources/images/Player Arrow - C.png");
				break;
		}

		String path = imgURL.toExternalForm();
		Image pic = new Image(path);

		arrowImg.setImage(pic);
		arrowType = index;
	}
	
	public void backToMap()
	{
		playBtnSound();
		Main.changeScene(SceneType.MAP);
	}

	public static Color getBtnColor()
	{
		return btnColor;
	}

	public static Color getDoorColor()
	{
		return doorColor;
	}

	public static Color getBtnHighlightColor()
	{
		return btnHighlightColor;
	}

	public static Color getDoorHighlightColor()
	{
		return doorHighlightColor;
	}
	
	public static Image getArrowType()
	{
		URL imgURL = null;
		
		switch(arrowType)
		{
			case 1:
				imgURL = CustomizeController.class.getResource("/resources/images/Player Arrow - A.png");
				break;

			case 2:
				imgURL = CustomizeController.class.getResource("/resources/images/Player Arrow - B.png");
				break;

			case 3:
				imgURL = CustomizeController.class.getResource("/resources/images/Player Arrow - C.png");
				break;
		}

		if(imgURL == null)
			return null;
		
		String path = imgURL.toExternalForm();
		Image pic = new Image(path);
		
		return pic;
	}
	
	private void playBtnSound()
	{		
		Media soundFX = new Media(this.getClass().getResource("/resources/sounds/Button.mp3").toExternalForm());
		MediaPlayer player = new MediaPlayer(soundFX);
		player.play();
	}
}
