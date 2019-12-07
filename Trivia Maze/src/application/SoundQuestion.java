package application;

import java.util.ArrayList;

import application.enums.QuestionType;

public class SoundQuestion extends Question
{
	private static final long serialVersionUID = 1803684093041585047L;
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
