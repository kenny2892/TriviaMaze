package application;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question
{
	private static final long serialVersionUID = -2863535289492776493L;

	public MultipleChoiceQuestion(String question, ArrayList<String> answers, int correctIndex)
	{
		super(EQuestionType.MULTIPLE_CHOICE, question, answers, correctIndex);
	}
}
