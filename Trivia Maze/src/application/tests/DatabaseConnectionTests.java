package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import application.DatabaseConnection;
import application.EDatabaseType;

class DatabaseConnectionTests
{

	@Test
	void dbconnector_throwsIllegalArgumentExceptionIfEnumTypeIsNull_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			DatabaseConnection.dbConnector(null);
		});
		assertEquals("Null Database Type", exception.getMessage());
	}

	@Test
	void dbConnector_returnedConnectionIsNotNullJava_TRUE()
	{
		Connection test = DatabaseConnection.dbConnector(EDatabaseType.Java);
		assertTrue(test != null);
	}

	@Test
	void dbConnector_returnedConnectionIsNotNullAnime_TRUE()
	{
		Connection test = DatabaseConnection.dbConnector(EDatabaseType.Anime);
		assertTrue(test != null);
	}

	@Test
	void dbConnector_returnedConnectionIsNotNullVideoGames_TRUE()
	{
		Connection test = DatabaseConnection.dbConnector(EDatabaseType.Video_Games);
		assertTrue(test != null);
	}

}
