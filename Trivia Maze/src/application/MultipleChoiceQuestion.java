package application;

import java.util.ArrayList;

import application.enums.QuestionType;

public class MultipleChoiceQuestion extends Question
{
	private static final long serialVersionUID = -2863535289492776493L;

	public MultipleChoiceQuestion(String question, ArrayList<String> answers, int correctIndex)
	{
		super(QuestionType.MULTIPLE_CHOICE, question, answers, correctIndex);
	}
}
