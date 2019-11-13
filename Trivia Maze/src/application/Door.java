package application;

public class Door
{
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
