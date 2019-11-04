package application;

import java.io.File;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
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
	@FXML
	private ImageView customizeImg;

	@FXML
	private ImageView saveImg;

	@FXML
	private ImageView loadImg;

	@FXML
	private ImageView settingsImg;
	
	@FXML
	private Rectangle customizeHitBox;

	@FXML
	private Rectangle saveHitBox;

	@FXML
	private Rectangle loadHitBox;

	@FXML
	private Rectangle settingsHitBox;
	
	@FXML
	private ImageView nDoorImg;

	@FXML
	private ImageView sDoorImg;

	@FXML
	private ImageView wDoorImg;

	@FXML
	private ImageView eDoorImg;
	
	@FXML
	private Rectangle nDoorHitBox;

	@FXML
	private Rectangle sDoorHitBox;

	@FXML
	private Rectangle wDoorHitBox;

	@FXML
	private Rectangle eDoorHitBox;
	
	public void initialize()
	{
		setClippingMasks();
		setDefaultColors();
		setDefaultHighlightColors();
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
	
	private void setDefaultColors()
	{
		changeImageColor(customizeImg, Color.web("EE793C"));
		changeImageColor(saveImg, Color.web("EE793C"));
		changeImageColor(loadImg, Color.web("EE793C"));
		changeImageColor(settingsImg, Color.web("EE793C"));

		changeImageColor(nDoorImg, Color.WHITE);
		changeImageColor(sDoorImg, Color.WHITE);
		changeImageColor(eDoorImg, Color.WHITE);
		changeImageColor(wDoorImg, Color.WHITE);
	}
	
	private void setDefaultHighlightColors()
	{
		changeHoverColor(customizeImg, customizeHitBox, Color.web("F7FE40"));
		changeHoverColor(saveImg, saveHitBox, Color.web("F7FE40"));
		changeHoverColor(loadImg, loadHitBox, Color.web("F7FE40"));
		changeHoverColor(settingsImg, settingsHitBox, Color.web("F7FE40"));

		changeHoverColor(nDoorImg, nDoorHitBox, Color.web("F7FE40"));
		changeHoverColor(sDoorImg, sDoorHitBox, Color.web("F7FE40"));
		changeHoverColor(eDoorImg, eDoorHitBox, Color.web("F7FE40"));
		changeHoverColor(wDoorImg, wDoorHitBox, Color.web("F7FE40"));
	}
	
	public void customizeBtn()
	{
		
	}
	
	public void saveBtn()
	{
		
	}
	
	public void loadBtn()
	{
		
	}
	
	public void settingsBtn()
	{
		
	}
	
	private void changeImageColor(ImageView imageToChange, Color color)
	{
		ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        Blend colorize = new Blend
        (
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput
                (
                        0,
                        0,
                        imageToChange.getImage().getWidth(),
                        imageToChange.getImage().getHeight(),
                        color
           		)
        );
        
        imageToChange.setEffect(colorize);

        imageToChange.setCache(true);
        imageToChange.setCacheHint(CacheHint.SPEED);
	}
	
	private void changeHoverColor(ImageView imageToChange, Rectangle hitBox, Color color)
	{
		ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        Blend colorize = new Blend
        (
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput
                (
                        0,
                        0,
                        imageToChange.getImage().getWidth(),
                        imageToChange.getImage().getHeight(),
                        color
                )
        );

        imageToChange.effectProperty().bind(
                Bindings
                    .when(hitBox.hoverProperty())
                        .then((Effect) colorize)
                        .otherwise((Effect) null)
        );

        imageToChange.setCache(true);
        imageToChange.setCacheHint(CacheHint.SPEED);
	}
}
