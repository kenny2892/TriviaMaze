package application.controllers;

import application.KeyBindings;
import application.Main;
import application.SceneType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class SettingsController
{
	@FXML private TextArea northKeyTxt, southKeyTxt, westKeyTxt, eastKeyTxt;
	@FXML private TextArea customizeKeyTxt, settingsKeyTxt, saveKeyTxt, loadKeyTxt, helpKeyTxt;
	@FXML private Text invalidInput;

	private KeyBindings bindings;
	
	public void initialize()
	{
		bindings = new KeyBindings();
		invalidInput.setVisible(false);
		
		northKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{					
					northKeyTxt.setText(event.getCode().toString());
					bindings.setNorth(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		southKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					southKeyTxt.setText(event.getCode().toString());
					bindings.setSouth(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		westKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					westKeyTxt.setText(event.getCode().toString());
					bindings.setWest(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		eastKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					eastKeyTxt.setText(event.getCode().toString());
					bindings.setEast(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		customizeKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					customizeKeyTxt.setText(event.getCode().toString());
					bindings.setCustomize(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		settingsKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					settingsKeyTxt.setText(event.getCode().toString());
					bindings.setSettings(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		saveKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					saveKeyTxt.setText(event.getCode().toString());
					bindings.setSave(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		loadKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					loadKeyTxt.setText(event.getCode().toString());
					bindings.setLoad(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
			}
		});
		
		helpKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);
				
				if(!bindings.usesKey(event.getCode()))
				{
					helpKeyTxt.setText(event.getCode().toString());
					bindings.setHelp(event.getCode());
				}
				
				else
					invalidInput.setVisible(true);
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
		
		invalidInput.setVisible(false);
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
