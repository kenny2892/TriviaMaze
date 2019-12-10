package application.controllers;

import application.Main;
import application.enums.DatabaseType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class StartController
{
	public void javaDatabase()
	{
		playBtnSound();
		Main.setUp(DatabaseType.JAVA);
	}
	
	public void animeDatabase()
	{
		playBtnSound();
		Main.setUp(DatabaseType.ANIME);
	}
	
	public void videoGamesDatabase()
	{
		playBtnSound();
		Main.setUp(DatabaseType.VIDEO_GAMES);
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
		player.setVolume(Main.getVolume());
		player.play();
	}
}
