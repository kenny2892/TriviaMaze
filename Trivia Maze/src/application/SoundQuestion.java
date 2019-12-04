package application;

import java.util.ArrayList;

public class SoundQuestion extends Question
{
	private String fileName;
	
	public SoundQuestion(String question, ArrayList<String> answers, int correctIndex, String fileName)
	{
		super(QuestionType.SOUND, question, answers, correctIndex);
		
		if(fileName == null)
			throw new IllegalArgumentException("fileName is null.");
		
		this.fileName = fileName;
	}
	
	public String getFileName()
	{
		return fileName;
	}
}
