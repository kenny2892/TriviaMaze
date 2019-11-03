package application;

public class Room
{
	private Door northDoor;
	private Door southDoor;
	private Door westDoor;
	private Door eastDoor;

	public Room(Door northDoor, Door southDoor, Door eastDoor, Door westDoor)
	{
		if(northDoor == null)
			throw new IllegalArgumentException("The northDoor parameter was passed as null.");
		
		else if(southDoor == null)
			throw new IllegalArgumentException("The southDoor parameter was passed as null.");
		
		else if(westDoor == null)
			throw new IllegalArgumentException("The westDoor parameter was passed as null.");
		
		else if(eastDoor == null)
			throw new IllegalArgumentException("The eastDoor parameter was passed as null.");
		
		this.northDoor = northDoor;
		this.southDoor = southDoor;
		this.eastDoor = eastDoor;
		this.westDoor = westDoor;
	}

	public Door getDoor(Direction direction)
	{
		switch(direction)
		{
			case NORTH:
				return northDoor;

			case EAST:
				return eastDoor;

			case WEST:
				return westDoor;

			case SOUTH:
				return southDoor;

			default:
				return new NullDoor();
		}
	}

	public boolean setDoor(Direction direction, Door door)
	{
		if(door == null)
			return false;
		
		switch(direction)
		{
			case NORTH:
				northDoor = door;
				break;

			case EAST:
				eastDoor = door;
				break;

			case WEST:
				westDoor = door;
				break;

			case SOUTH:
				southDoor = door;
				break;

			default:
				return false;
		}
		
		return true;
	}
}
