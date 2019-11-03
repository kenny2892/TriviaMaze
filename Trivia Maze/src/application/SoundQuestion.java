package application;

import java.util.ArrayList;

public class SoundQuestion extends Question
{
	public SoundQuestion(String question, ArrayList<String> answers, int correctIndex)
	{
		super(QuestionType.SOUND, question, answers, correctIndex);
	}
}
