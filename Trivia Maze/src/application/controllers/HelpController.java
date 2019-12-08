package application.controllers;

import application.Main;
import application.enums.SceneType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HelpController
{
	public void backToMap()
	{
		playBtnSound();
		Main.changeScene(SceneType.MAP);
	}
	
	private void playBtnSound()
	{		
		Media soundFX = new Media(this.getClass().getResource("/resources/sounds/Button.mp3").toExternalForm());
		MediaPlayer player = new MediaPlayer(soundFX);
		player.play();
	}
}
