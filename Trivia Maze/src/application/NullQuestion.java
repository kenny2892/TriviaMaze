package application;

import java.util.ArrayList;

public class NullQuestion extends Question
{
	public NullQuestion()
	{
		super(QuestionType.NULL, "", new ArrayList<String>(), -1);
	}
}
