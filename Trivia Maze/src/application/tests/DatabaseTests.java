package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Database;
import application.EDatabaseType;
import application.Question;

class DatabaseTests {
		
	@Test
	void getInstanceOfDatabase_doesNotReturnNull_TRUE ()
	{
		Database sut = Database.getInstanceOfDatabase();
		assertTrue(sut != null);
	}
	
	@Test
	void createQuestions_incorrectParameterThrowsException_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			Database sut = Database.getInstanceOfDatabase();
			sut.createQuestions(null);
		});
		assertEquals("Null Database Type", exception.getMessage());
	}
	
	@Test
	void getQuestion_throwNullPointerExceptionWhenNoMoreQuestions_TRUE()
	{
		
		NullPointerException exception = assertThrows(NullPointerException.class, () ->
		{
			Database sut = Database.getInstanceOfDatabase();
			sut.createQuestions(EDatabaseType.Java);
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
		Database sut = Database.getInstanceOfDatabase();
		sut.createQuestions(EDatabaseType.Java);
		@SuppressWarnings("unused")
		Question test;
		boolean trigger = false;

			try
			{
				while((test = sut.getQuestion()) != null);
			}
			catch (NullPointerException e)
			{
				trigger = true;
			}

		assertTrue(trigger);
	}
	
	@Test
	void getQuestion_eachQuestionOfAnimeIsNotNull_TRUE()
	{
		Database sut = Database.getInstanceOfDatabase();
		sut.createQuestions(EDatabaseType.Anime);
		@SuppressWarnings("unused")
		Question test;
		boolean trigger = false;

			try
			{
				while((test = sut.getQuestion()) != null);
			}
			catch (NullPointerException e)
			{
				trigger = true;
			}

		assertTrue(trigger);
	}
	
	@Test
	void getQuestion_eachQuestionOfVideoGamesIsNotNull_TRUE()
	{
		Database sut = Database.getInstanceOfDatabase();
		sut.createQuestions(EDatabaseType.Video_Games);
		@SuppressWarnings("unused")
		Question test;
		boolean trigger = false;

			try
			{
				while((test = sut.getQuestion()) != null);
			}
			catch (NullPointerException e)
			{
				trigger = true;
			}

		assertTrue(trigger);
	}

}
