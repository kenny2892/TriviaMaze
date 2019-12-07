package application.controllers;

import application.Main;
import application.enums.DatabaseType;

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
