package application;

public class Room
{
	private Door northDoor, southDoor, eastDoor, westDoor;

	public Room(Door northDoor, Door southDoor, Door eastDoor, Door westDoor)
	{
		if(northDoor == null)
			throw new IllegalArgumentException("The northDoor parameter was passed as null.");

		else if(southDoor == null)
			throw new IllegalArgumentException("The southDoor parameter was passed as null.");

		else if(eastDoor == null)
			throw new IllegalArgumentException("The eastDoor parameter was passed as null.");

		else if(westDoor == null)
			throw new IllegalArgumentException("The westDoor parameter was passed as null.");
		
		this.northDoor = northDoor;
		this.southDoor = southDoor;
		this.eastDoor = eastDoor;
		this.westDoor = westDoor;
	}

	public boolean isDoorLocked(Direction direction)
	{
		if(direction == null)
			throw new IllegalArgumentException("Passed in direction is null.");
		
		switch(direction)
		{
			case NORTH:
				return northDoor.isLocked();

			case EAST:
				return eastDoor.isLocked();

			case WEST:
				return westDoor.isLocked();

			case SOUTH:
				return southDoor.isLocked();

			default:
				throw new IllegalArgumentException("Invalid Direction");
		}
	}

	public boolean isDoorOpened(Direction direction)
	{
		if(direction == null)
			throw new IllegalArgumentException("Passed in direction is null.");
		
		switch(direction)
		{
			case NORTH:
				return northDoor.isOpen();

			case EAST:
				return eastDoor.isOpen();

			case WEST:
				return westDoor.isOpen();

			case SOUTH:
				return southDoor.isOpen();

			default:
				throw new IllegalArgumentException("Invalid Direction");
		}
	}

	public boolean setDoorLock(Direction direction, boolean isLocked)
	{
		if(direction == null)
			throw new IllegalArgumentException("Passed in direction is null.");
		
		switch(direction)
		{
			case NORTH:
				northDoor.setLocked(isLocked);
				northDoor.open();
				break;

			case EAST:
				eastDoor.setLocked(isLocked);
				eastDoor.open();
				break;

			case WEST:
				westDoor.setLocked(isLocked);
				westDoor.open();
				break;

			case SOUTH:
				southDoor.setLocked(isLocked);
				southDoor.open();
				break;

			default:
				throw new IllegalArgumentException("Invalid Direction");
		}
		
		return true;
	}
}
