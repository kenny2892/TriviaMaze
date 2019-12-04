package application;

import java.io.Serializable;

public class Door implements Serializable
{
	private static final long serialVersionUID = 1737533880605753215L;
	private boolean isLocked;
	private boolean opened;

	public Door(boolean isLocked)
	{
		this.isLocked = isLocked;
	}

	public boolean isLocked()
	{
		return this.isLocked;
	}

	public void setLocked(boolean isLocked)
	{
		this.isLocked = isLocked;
	}
	
	public void open()
	{
		opened = true;
	}
	
	public boolean isOpen()
	{
		return opened;
	}
}
