package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import application.TrueFalseQuestion;
import application.enums.QuestionType;

class QuestionTests
{
	@Test
	void questionsConstructor_throwsIllegalArgumentExceptionWhenPassedNullQuestionString_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("1");
			@SuppressWarnings("unused")
			TrueFalseQuestion sut = new TrueFalseQuestion(null, answers, 0);
		});
		assertEquals("The question parameter was passed as null.", exception.getMessage());
	}
	
	@Test
	void questionsConstructor_throwsIllegalArgumentExceptionWhenPassedNullArrayList_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			@SuppressWarnings("unused")
			TrueFalseQuestion sut = new TrueFalseQuestion("", null, 0);
		});
		assertEquals("The answers parameter was passed as null.", exception.getMessage());
	}
	
	@Test
	void questionsConstructor_throwsIllegalArgumentExceptionWhenPassedIndexOutOfBounds_TRUE()
	{
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		{
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("1");
			@SuppressWarnings("unused")
			TrueFalseQuestion sut = new TrueFalseQuestion("", answers, -1);
		});
		assertEquals("The correctIndex parameter was greater than or equal to the length of the \"answers\" parameter.", exception.getMessage());
	}
	
	@Test
	void getQuestionsType_returnsCorrectVlaueOfQuestionsType_TRUE()
	{
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("1");
		TrueFalseQuestion sut = new TrueFalseQuestion("", answers, 0);
		assertTrue(sut.getQuestionsType() == QuestionType.TRUE_FALSE);
	}
	
	@Test
	void getQuestionsType_returnsCorrectVlaueOfQuestion_TRUE()
	{
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("1");
		TrueFalseQuestion sut = new TrueFalseQuestion("", answers, 0);
		assertTrue(sut.getQuestion().contentEquals(""));
	}
	
	@Test
	void getQuestionsType_returnsCorrectVlaueOfAnswers_TRUE()
	{
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("1");
		TrueFalseQuestion sut = new TrueFalseQuestion("", answers, 0);
		assertTrue(sut.getAnswers() == answers);
	}
	
	@Test
	void getQuestionsType_returnsCorrectVlaueOfCorrectIndex_TRUE()
	{
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("1");
		TrueFalseQuestion sut = new TrueFalseQuestion("", answers, 0);
		assertTrue(sut.getCorrectIndex() == 0);
	}
}
