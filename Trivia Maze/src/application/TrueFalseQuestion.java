package application;

import java.util.ArrayList;

import application.enums.QuestionType;

public class TrueFalseQuestion extends Question
{
	private static final long serialVersionUID = 4816361754788452921L;

	public TrueFalseQuestion(String question, ArrayList<String> answers, int correctIndex)
	{
		super(QuestionType.TRUE_FALSE, question, answers, correctIndex);
	}
}
