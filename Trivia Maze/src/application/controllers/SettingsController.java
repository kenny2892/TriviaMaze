package application.controllers;

import application.KeyBindings;
import application.Main;
import application.SceneType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class SettingsController
{
	@FXML private TextArea northKeyTxt, southKeyTxt, westKeyTxt, eastKeyTxt;
	@FXML private TextArea customizeKeyTxt, settingsKeyTxt, saveKeyTxt, loadKeyTxt, helpKeyTxt;

	private KeyBindings bindings;
	
	public void initialize()
	{
		bindings = new KeyBindings();
		
		northKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				northKeyTxt.setText(event.getCode().toString());
				bindings.setNorth(event.getCode());
			}
		});
		
		southKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				southKeyTxt.setText(event.getCode().toString());
				bindings.setSouth(event.getCode());
			}
		});
		
		westKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				westKeyTxt.setText(event.getCode().toString());
				bindings.setWest(event.getCode());
			}
		});
		
		eastKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				eastKeyTxt.setText(event.getCode().toString());
				bindings.setEast(event.getCode());
			}
		});
		
		customizeKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				customizeKeyTxt.setText(event.getCode().toString());
				bindings.setCustomize(event.getCode());
			}
		});
		
		settingsKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				settingsKeyTxt.setText(event.getCode().toString());
				bindings.setSettings(event.getCode());
			}
		});
		
		saveKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				saveKeyTxt.setText(event.getCode().toString());
				bindings.setSave(event.getCode());
			}
		});
		
		loadKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				loadKeyTxt.setText(event.getCode().toString());
				bindings.setLoad(event.getCode());
			}
		});
		
		helpKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				helpKeyTxt.setText(event.getCode().toString());
				bindings.setHelp(event.getCode());
			}
		});
	}
	
	public void update()
	{
		northKeyTxt.setText(bindings.getNorth().toString());
		southKeyTxt.setText(bindings.getSouth().toString());
		westKeyTxt.setText(bindings.getWest().toString());
		eastKeyTxt.setText(bindings.getEast().toString());

		customizeKeyTxt.setText(bindings.getCustomize().toString());
		settingsKeyTxt.setText(bindings.getSettings().toString());
		saveKeyTxt.setText(bindings.getSave().toString());
		loadKeyTxt.setText(bindings.getLoad().toString());
		helpKeyTxt.setText(bindings.getHelp().toString());
	}
	
	public KeyBindings getKeyBindings()
	{
		return bindings;
	}
	
	public void backToMap()
	{
		Main.changeScene(SceneType.MAP);
	}
}
