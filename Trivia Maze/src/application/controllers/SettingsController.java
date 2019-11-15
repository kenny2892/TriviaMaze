package application.controllers;

import application.Main;
import application.SceneType;

public class SettingsController
{
	public void backToMap()
	{
		Main.changeScene(SceneType.MAP);
	}
}
