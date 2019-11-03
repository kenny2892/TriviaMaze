package application;

import java.util.ArrayList;

public class VideoQuestion extends Question
{
	public VideoQuestion(String question, ArrayList<String> answers, int correctIndex)
	{
		super(QuestionType.VIDEO, question, answers, correctIndex);
	}
}
