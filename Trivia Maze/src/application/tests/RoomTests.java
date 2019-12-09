package application.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import application.Door;
import application.Room;

class RoomTests
{

	@Test
	void roomContrsuctor_throwsIllegalArgumentExceptionIfNorthDoorIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			new Room(null, new Door(true), new Door(true), new Door(true));
		});
		assertEquals("The northDoor parameter was passed as null.", exception.getMessage());
	}
	
	@Test
	void roomContrsuctor_throwsIllegalArgumentExceptionIfSouthDoorIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			new Room(new Door(true), null, new Door(true), new Door(true));
		});
		assertEquals("The southDoor parameter was passed as null.", exception.getMessage());
	}
	
	@Test
	void roomContrsuctor_throwsIllegalArgumentExceptionIfEastDoorIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			new Room(new Door(true), new Door(true), null, new Door(true));
		});
		assertEquals("The eastDoor parameter was passed as null.", exception.getMessage());
	}
	
	@Test
	void roomContrsuctor_throwsIllegalArgumentExceptionIfWestDoorIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			new Room(new Door(true), new Door(true), new Door(true), null);
		});
		assertEquals("The westDoor parameter was passed as null.", exception.getMessage());
	}
	
	@Test
	void isDoorLocked_throwsIllegalArgumentExceptionIfDrectionIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Room sut = new Room(new Door(true), new Door(true), new Door(true), new Door(true));
			sut.isDoorOpened(null);
		});
		assertEquals("Passed in direction is null.", exception.getMessage());
	}
	
	@Test
	void setDoorLock_throwsIllegalArgumentExceptionIfDrectionIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Room sut = new Room(new Door(true), new Door(true), new Door(true), new Door(true));
			sut.setDoorLock(null, true);
		});
		assertEquals("Passed in direction is null.", exception.getMessage());
	}

}
