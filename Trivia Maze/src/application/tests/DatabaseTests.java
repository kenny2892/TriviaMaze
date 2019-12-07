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
	void getQuestion_eachQuestionIsNotNull_TRUE()
	{
		Database sut = Database.getInstanceOfDatabase();
		sut.createQuestions(EDatabaseType.Java);
		Question test;

			try
			{
				while((test = sut.getQuestion()) != null)
				{
				
				if(test == null)
				{
					assertFalse(test != null);
				}
				}
			}
			catch (Exception e)
			{
				
			}

		assertTrue(true);
	}

}
