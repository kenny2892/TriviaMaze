package application;

import java.io.Serializable;
import java.util.ArrayList;

import application.enums.QuestionType;

public abstract class Question implements Serializable
{
	private static final long serialVersionUID = -4243606643126312760L;
	private QuestionType type;
	private String question;
	private ArrayList<String> answers;
	private int correctIndex;

	public Question(QuestionType type, String question, ArrayList<String> answers, int correctIndex)
	{
		if(type == null)
			throw new IllegalArgumentException("The type parameter was passed as null.");

		else if(question == null)
			throw new IllegalArgumentException("The question parameter was passed as null.");

		else if(answers == null)
			throw new IllegalArgumentException("The answers parameter was passed as null.");

		else if(correctIndex >= answers.size() || correctIndex < 0)
			throw new IllegalArgumentException("The correctIndex parameter was greater than or equal to the length of the \"answers\" parameter.");
		
		this.type = type;
		this.question = question;
		this.answers = answers;
		this.correctIndex = correctIndex;
	}

	public QuestionType getQuestionsType()
	{
		return this.type;
	}

	public String getQuestion()
	{
		return this.question;
	}

	public ArrayList<String> getAnswers()
	{
		return this.answers;
	}

	public int getCorrectIndex()
	{
		return this.correctIndex;
	}
}
