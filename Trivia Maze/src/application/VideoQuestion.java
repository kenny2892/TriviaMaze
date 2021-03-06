package application;

import java.util.ArrayList;

import application.enums.QuestionType;

public class VideoQuestion extends Question
{
	private static final long serialVersionUID = -5899766019568275257L;
	private String fileName;
	
	public VideoQuestion(String question, ArrayList<String> answers, int correctIndex, String fileName)
	{
		super(QuestionType.VIDEO, question, answers, correctIndex);
		
		if(fileName == null)
			throw new IllegalArgumentException("fileName is null.");
		
		this.fileName = fileName;
	}
	
	public String getFileName()
	{
		return fileName;
	}
}
