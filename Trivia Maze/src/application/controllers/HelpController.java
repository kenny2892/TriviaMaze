package application.controllers;

import application.Main;
import application.enums.SceneType;

public class HelpController
{
	public void backToMap()
	{
		Main.changeScene(SceneType.MAP);
	}
}
