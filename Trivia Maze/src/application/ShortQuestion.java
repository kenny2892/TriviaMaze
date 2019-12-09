package application;

import java.util.ArrayList;

import application.enums.QuestionType;

public class ShortQuestion extends Question
{
	private static final long serialVersionUID = -3184211847835066725L;

	public ShortQuestion(String question, ArrayList<String> keywords)
	{
		super(QuestionType.SHORT, question, keywords, 0);
	}
}
