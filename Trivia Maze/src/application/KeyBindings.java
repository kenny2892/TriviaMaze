package application;

import javafx.scene.input.KeyCode;

public class KeyBindings
{
	private KeyCode north, south, east, west, customize, save, load, settings, help, cheatIdentifier;

	public KeyBindings()
	{
		north = KeyCode.UP;
		south = KeyCode.DOWN;
		east = KeyCode.RIGHT;
		west = KeyCode.LEFT;

		customize = KeyCode.Q;
		save = KeyCode.W;
		load = KeyCode.E;
		settings = KeyCode.R;
		help = KeyCode.T;
		cheatIdentifier = KeyCode.SLASH;
	}

	public KeyCode getNorth()
	{
		return north;
	}

	public void setNorth(KeyCode north)
	{
		this.north = north;
	}

	public KeyCode getSouth()
	{
		return south;
	}

	public void setSouth(KeyCode south)
	{
		this.south = south;
	}

	public KeyCode getEast()
	{
		return east;
	}

	public void setEast(KeyCode east)
	{
		this.east = east;
	}

	public KeyCode getWest()
	{
		return west;
	}

	public void setWest(KeyCode west)
	{
		this.west = west;
	}

	public KeyCode getCustomize()
	{
		return customize;
	}

	public void setCustomize(KeyCode customize)
	{
		this.customize = customize;
	}

	public KeyCode getSave()
	{
		return save;
	}

	public void setSave(KeyCode save)
	{
		this.save = save;
	}

	public KeyCode getLoad()
	{
		return load;
	}

	public void setLoad(KeyCode load)
	{
		this.load = load;
	}

	public KeyCode getSettings()
	{
		return settings;
	}

	public void setSettings(KeyCode settings)
	{
		this.settings = settings;
	}

	public KeyCode getHelp()
	{
		return help;
	}

	public void setHelp(KeyCode help)
	{
		this.help = help;
	}

	public KeyCode getCheatIdentifier()
	{
		return cheatIdentifier;
	}

	public boolean usesKey(KeyCode key)
	{
		if (key == null)
			return false;

		else if (key.equals(north) || key.equals(south) || key.equals(west) || key.equals(east) 
				|| key.equals(customize) || key.equals(save) || key.equals(load) || key.equals(settings) || key.equals(help)
				|| key.equals(cheatIdentifier))
			return true;

		return false;
	}
}
