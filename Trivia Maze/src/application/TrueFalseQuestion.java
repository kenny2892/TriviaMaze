package application;

import java.util.ArrayList;

public class TrueFalseQuestion extends Question
{
	public TrueFalseQuestion(String question, ArrayList<String> answers, int correctIndex)
	{
		super(QuestionType.TRUE_FALSE, question, answers, correctIndex);
	}
}
