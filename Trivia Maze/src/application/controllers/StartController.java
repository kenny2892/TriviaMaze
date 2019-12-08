package application.controllers;

import application.Main;
import application.enums.DatabaseType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class StartController
{
	public void javaDatabase()
	{
//		playBtnSound();
		Main.setUp(DatabaseType.Java);
	}
	
	public void animeDatabase()
	{
		playBtnSound();
		Main.setUp(DatabaseType.Anime);
	}
	
	public void videoGamesDatabase()
	{
		playBtnSound();
		Main.setUp(DatabaseType.Video_Games);
	}
	
	public void openGitHub()
	{
		playBtnSound();
		Main.openGitHub();
	}
	
	private void playBtnSound()
	{		
		Media soundFX = new Media(this.getClass().getResource("/resources/sounds/Button.mp3").toExternalForm());
		MediaPlayer player = new MediaPlayer(soundFX);
		player.play();
	}
}
