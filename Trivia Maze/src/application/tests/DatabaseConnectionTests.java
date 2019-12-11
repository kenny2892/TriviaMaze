package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import application.DatabaseConnection;
import application.enums.DatabaseType;

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
		Connection test = DatabaseConnection.dbConnector(DatabaseType.JAVA);
		assertTrue(test != null);
	}

	@Test
	void dbConnector_returnedConnectionIsNotNullAnime_TRUE()
	{
		Connection test = DatabaseConnection.dbConnector(DatabaseType.ANIME);
		assertTrue(test != null);
	}

	@Test
	void dbConnector_returnedConnectionIsNotNullVideoGames_TRUE()
	{
		Connection test = DatabaseConnection.dbConnector(DatabaseType.VIDEO_GAMES);
		assertTrue(test != null);
	}
}
