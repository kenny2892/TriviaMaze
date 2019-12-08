package application.controllers;

import application.KeyBindings;
import application.Main;
import application.enums.SceneType;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class SettingsController
{
	@FXML private TextArea northKeyTxt, southKeyTxt, westKeyTxt, eastKeyTxt;
	@FXML private TextArea customizeKeyTxt, settingsKeyTxt, saveKeyTxt, loadKeyTxt, helpKeyTxt;
	@FXML private Text invalidInput, clearTxt;

	private KeyBindings bindings;

	public void initialize()
	{
		bindings = new KeyBindings();
		invalidInput.setVisible(false);

		setShowClearTxt();
		setBindings();
	}
	
	private void setBindings()
	{
		northKeyTxt.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				invalidInput.setVisible(false);

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					northKeyTxt.setText(txt);
					bindings.setNorth(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					southKeyTxt.setText(txt);
					bindings.setSouth(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					westKeyTxt.setText(txt);
					bindings.setWest(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					eastKeyTxt.setText(txt);
					bindings.setEast(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					customizeKeyTxt.setText(txt);
					bindings.setCustomize(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					settingsKeyTxt.setText(txt);
					bindings.setSettings(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					saveKeyTxt.setText(txt);
					bindings.setSave(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					loadKeyTxt.setText(txt);
					bindings.setLoad(key);
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

				if (!bindings.usesKey(event.getCode()))
				{
					KeyCode key = event.getCode();
					String txt = key.toString();

					if (event.getCode().equals(KeyCode.DELETE))
					{
						key = null;
						txt = "";
					}

					helpKeyTxt.setText(txt);
					bindings.setHelp(key);
				}

				else
					invalidInput.setVisible(true);
			}
		});
	}

	private void setShowClearTxt()
	{
		northKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		southKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		westKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		eastKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		customizeKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		settingsKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		saveKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		loadKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
		});
		
		helpKeyTxt.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) ->
		{
			clearTxt.setVisible(newValue);
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
		clearTxt.setVisible(false);
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
