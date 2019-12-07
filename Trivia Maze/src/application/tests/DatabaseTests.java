package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.*;
import application.enums.DatabaseType;

class DatabaseTests
{
	@Test
	void database_correctParameters_TRUE()
	{
		Database sut = new Database(DatabaseType.Java);
		assertTrue(sut != null);

		sut = new Database(DatabaseType.Anime);
		assertTrue(sut != null);

		sut = new Database(DatabaseType.Video_Games);
		assertTrue(sut != null);
	}

	@Test
	void database_nullParameterThrowsException_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			new Database(null);
		});
		assertEquals("Null Database Type", exception.getMessage());
	}

	@Test
	void getQuestion_throwNullPointerExceptionWhenNoMoreQuestions_TRUE()
	{

		NullPointerException exception = assertThrows(NullPointerException.class, () ->
		{
			Database sut = new Database(DatabaseType.Java);
			@SuppressWarnings("unused")
			Question test;
			while(true)
			{
				test = sut.getQuestion();
			}
		});
		
		assertEquals("Questions ArrayList is empty", exception.getMessage());
	}

	@Test
	void getQuestion_eachQuestionOfJavaIsNotNull_TRUE()
	{
		assertTrue(checkEachQuestion(new Database(DatabaseType.Java)));
	}

	@Test
	void getQuestion_eachQuestionOfAnimeIsNotNull_TRUE()
	{
		assertTrue(checkEachQuestion(new Database(DatabaseType.Anime)));
	}

	@Test
	void getQuestion_eachQuestionOfVideoGamesIsNotNull_TRUE()
	{

		assertTrue(checkEachQuestion(new Database(DatabaseType.Video_Games)));
	}
	
	private boolean checkEachQuestion(Database sut)
	{
			@SuppressWarnings("unused")
			Question test;
			boolean trigger = false;

			try
			{
				while((test = sut.getQuestion()) != null)
					;
			}
			catch(NullPointerException e)
			{
				trigger = true;
			}

			return trigger;
	}
}
