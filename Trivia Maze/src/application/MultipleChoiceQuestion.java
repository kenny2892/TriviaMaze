package application;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question
{
	public MultipleChoiceQuestion(String question, ArrayList<String> answers, int correctIndex)
	{
		super(QuestionType.MULTIPLE_CHOICE, question, answers, correctIndex);
	}
}
