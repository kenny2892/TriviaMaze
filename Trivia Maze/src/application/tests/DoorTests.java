package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Door;

class DoorTests
{

	@Test
	void doorConstructor_getCorrectIslockedValueOfTrue_TRUE()
	{
		boolean isLocked = true;
		Door sut = new Door(isLocked);
		assertTrue(sut.isLocked() == isLocked);
	}
	
	@Test
	void doorConstructor_getCorrectIslockedValueOfFalse_TRUE()
	{
		boolean isLocked = false;
		Door sut = new Door(isLocked);
		assertTrue(sut.isLocked() == isLocked);
	}
	
	@Test
	void setLocked_setsTheCorrectValueOfTrue_TRUE()
	{
		boolean isLocked = true;
		Door sut = new Door(isLocked);
		sut.setLocked(!isLocked);
		assertTrue(sut.isLocked() == !isLocked);
	}
	
	@Test
	void setLocked_setsTheCorrectValueOfFalse_TRUE()
	{
		boolean isLocked = false;
		Door sut = new Door(isLocked);
		sut.setLocked(!isLocked);
		assertTrue(sut.isLocked() == !isLocked);
	}
	
	@Test
	void isOpen_defaultValueIsFalse_TRUE()
	{
		boolean isLocked = true;
		Door sut = new Door(isLocked);
		assertTrue(sut.isOpen() == false);
	}
	
	@Test
	void open_onceOpenIsCalledOpenedBecomesTrue_TRUE()
	{
		boolean isLocked = true;
		Door sut = new Door(isLocked);
		sut.open();
		assertTrue(sut.isOpen() == true);
	}

}
