package application.controllers;

import application.DatabaseType;
import application.Main;

public class StartController
{
	public void javaDatabase()
	{
		Main.setUp(DatabaseType.Java);
	}
	
	public void animeDatabase()
	{
		Main.setUp(DatabaseType.Anime);
	}
	
	public void videoGamesDatabase()
	{
		Main.setUp(DatabaseType.Video_Games);
	}
	
	public void openGitHub()
	{
		Main.openGitHub();
	}
}
