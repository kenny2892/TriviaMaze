package application;

public class NullDoor extends Door
{
	public NullDoor()
	{
		super(false, new NullQuestion());
	}
}
