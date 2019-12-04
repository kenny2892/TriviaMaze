package application;

import javafx.scene.input.KeyCode;

public class KeyBindings
{
	private KeyCode north, south, east, west, customize, save, load, settings, help;
	
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
	}

	public KeyCode getNorth()
	{
		return north;
	}

	public void setNorth(KeyCode north)
	{
		if(north == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.north = north;
	}

	public KeyCode getSouth()
	{
		return south;
	}

	public void setSouth(KeyCode south)
	{
		if(south == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.south = south;
	}

	public KeyCode getEast()
	{
		return east;
	}

	public void setEast(KeyCode east)
	{
		if(east == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.east = east;
	}

	public KeyCode getWest()
	{
		return west;
	}

	public void setWest(KeyCode west)
	{
		if(west == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.west = west;
	}

	public KeyCode getCustomize()
	{
		return customize;
	}

	public void setCustomize(KeyCode customize)
	{
		if(customize == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.customize = customize;
	}

	public KeyCode getSave()
	{
		return save;
	}

	public void setSave(KeyCode save)
	{
		if(save == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.save = save;
	}

	public KeyCode getLoad()
	{
		return load;
	}

	public void setLoad(KeyCode load)
	{
		if(load == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.load = load;
	}

	public KeyCode getSettings()
	{
		return settings;
	}

	public void setSettings(KeyCode settings)
	{
		if(settings == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.settings = settings;
	}

	public KeyCode getHelp()
	{
		return help;
	}

	public void setHelp(KeyCode help)
	{
		if(help == null)
			throw new IllegalArgumentException("Null Key Code");
		
		this.help = help;
	}
	
	
}
