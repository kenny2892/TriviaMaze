package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Main;
import application.enums.ArchwayStatus;
import application.enums.DatabaseType;

class MainTests
{

	@Test
	void setUp_throwsIllegalArgumentExceptionWhenDatabaseTypeIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Main.setUp(null);
		});
		assertEquals("Null Database Type", exception.getMessage());
	}
	
	@Test
	void setHorizontalArchway_throwsIllegalArgumentExceptionWhenStatusIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Main.setHorizontalArchway(0, 0, null);
		});
		assertEquals("Null Archway Status", exception.getMessage());
	}
	
	@Test
	void setHorizontalArchway_throwsIllegalArgumentExceptionWhenYIsLessThanZero_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Main.setHorizontalArchway(-1, 0, ArchwayStatus.LOCKED);
		});
		assertEquals("Invalid y value", exception.getMessage());
	}
	
	@Test
	void setHorizontalArchway_throwsIllegalArgumentExceptionWhenXIsLessThanZero_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Main.setHorizontalArchway(0, -1, ArchwayStatus.LOCKED);
		});
		assertEquals("Invalid x value", exception.getMessage());
	}
	
	@Test
	void setVerticalArchway_throwsIllegalArgumentExceptionWhenStatusIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Main.setHorizontalArchway(0, 0, null);
		});
		assertEquals("Null Archway Status", exception.getMessage());
	}
	
	@Test
	void setVerticalArchway_throwsIllegalArgumentExceptionWhenYIsLessThanZero_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Main.setHorizontalArchway(-1, 0, ArchwayStatus.LOCKED);
		});
		assertEquals("Invalid y value", exception.getMessage());
	}
	
	@Test
	void setVerticalArchway_throwsIllegalArgumentExceptionWhenXIsLessThanZero_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Main.setHorizontalArchway(0, -1, ArchwayStatus.LOCKED);
		});
		assertEquals("Invalid x value", exception.getMessage());
	}

}
