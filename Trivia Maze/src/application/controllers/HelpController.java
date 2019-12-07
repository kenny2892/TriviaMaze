package application.controllers;

import application.Main;
import application.ESceneType;

public class HelpController
{
	public void backToMap()
	{
		Main.changeScene(ESceneType.MAP);
	}
}
