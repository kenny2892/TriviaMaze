package application.controllers;

import application.EDatabaseType;
import application.Main;

public class StartController
{
	public void javaDatabase()
	{
		Main.setUp(EDatabaseType.Java);
	}
	
	public void animeDatabase()
	{
		Main.setUp(EDatabaseType.Anime);
	}
	
	public void videoGamesDatabase()
	{
		Main.setUp(EDatabaseType.Video_Games);
	}
	
	public void openGitHub()
	{
		Main.openGitHub();
	}
}
