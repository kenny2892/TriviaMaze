package application;

public class Room
{
	private Door northDoor, southDoor, eastDoor, westDoor;

	public Room(Door northDoor, Door southDoor, Door eastDoor, Door westDoor)
	{		
		this.northDoor = northDoor;
		this.southDoor = southDoor;
		this.eastDoor = eastDoor;
		this.westDoor = westDoor;
	}

	public boolean isDoorLocked(Direction direction)
	{
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
				return true;
		}
	}

	public boolean setDoorLock(Direction direction, boolean isLocked)
	{
		if(direction == null)
			throw new IllegalArgumentException("Invalid Direction");
		
		switch(direction)
		{
			case NORTH:
				northDoor.setLocked(isLocked);
				break;

			case EAST:
				eastDoor.setLocked(isLocked);
				break;

			case WEST:
				westDoor.setLocked(isLocked);
				break;

			case SOUTH:
				southDoor.setLocked(isLocked);
				break;

			default:
				throw new IllegalArgumentException("Invalid Direction");
		}
		
		return true;
	}
}
