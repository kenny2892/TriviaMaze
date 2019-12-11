package application;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import application.enums.DatabaseType;

public class Database implements Serializable
{
	private static final long serialVersionUID = 5947245570442525353L;
	private Question currentQuestion;
	private ArrayList<Question> questions;

	public Database(DatabaseType type)
	{
		if (type == null)
			throw new IllegalArgumentException("Null Database Type");

		this.questions = createQuestionDatabase(type);
		
		if(questions == null)
			throw new IllegalArgumentException("Invalid Database Type");
	}

	public ArrayList<Question> createQuestionDatabase(DatabaseType type)
	{
		if (type == null)
			throw new IllegalArgumentException("Null Database Type");

		ArrayList<Question> array = new ArrayList<Question>();

		try
		{
			Connection connect = DatabaseConnection.dbConnector(type);

			array = getMultipleChoice(connect, array);
			array = getTrueFalse(connect, array);
			array = getShort(connect, array);
			array = getVideo(connect, array);
			array = getSound(connect, array);

			Collections.shuffle(array);

			connect.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		return array;
	}

	private ArrayList<Question> getMultipleChoice(Connection connect, ArrayList<Question> array)
	{
		try
		{
			String query = "Select * from MultipleChoiceQuestions";
			PreparedStatement pst = connect.prepareStatement(query);

			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String question = results.getString("Questions");
				String options = results.getString("Options");
				String answer = results.getString("Answers");

				ArrayList<String> optionAra = new ArrayList<String>(Arrays.asList(options.split("\n")));

				int indexOfAnswer = 0;

				for(int i = 0; i < optionAra.size(); i++)
				{
					if (optionAra.get(i).startsWith(answer.toLowerCase()))
						indexOfAnswer = i;

					optionAra.set(i, optionAra.get(i).substring(3));
				}

				MultipleChoiceQuestion mcQuestion = new MultipleChoiceQuestion(question, optionAra, indexOfAnswer);
				array.add(mcQuestion);
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return array;
	}

	private ArrayList<Question> getTrueFalse(Connection connect, ArrayList<Question> array)
	{
		try
		{
			String query = "Select * from TrueFalseQuestions";
			PreparedStatement pst = connect.prepareStatement(query);

			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String question = results.getString("Questions");
				String answer = results.getString("Answers");

				ArrayList<String> options = new ArrayList<String>();
				options.add("True");
				options.add("False");

				int index = 0;

				if (answer.toLowerCase().compareTo("false") == 0)
					index = 1;

				TrueFalseQuestion tfQuestion = new TrueFalseQuestion(question, options, index);
				array.add(tfQuestion);
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return array;
	}

	private ArrayList<Question> getShort(Connection connect, ArrayList<Question> array)
	{
		try
		{
			String query = "Select * from ShortAnswerQuestions";
			PreparedStatement pst = connect.prepareStatement(query);

			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String question = results.getString("Questions");
				String keywords = results.getString("Keywords");

				ShortQuestion shortQuestion = new ShortQuestion(question, new ArrayList<String>(Arrays.asList(keywords.split(" "))));
				array.add(shortQuestion);
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return array;
	}

	private ArrayList<Question> getVideo(Connection connect, ArrayList<Question> array)
	{
		try
		{
			String query = "Select * from VideoQuestions";
			PreparedStatement pst = connect.prepareStatement(query);

			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String question = results.getString("Questions");
				String options = results.getString("Options");
				String answer = results.getString("Answers");
				String fileName = results.getString("File");

				ArrayList<String> optionAra = new ArrayList<String>(Arrays.asList(options.split("\n")));

				int indexOfAnswer = 0;

				for(int i = 0; i < optionAra.size(); i++)
				{
					if (optionAra.get(i).startsWith(answer.toLowerCase()))
						indexOfAnswer = i;

					optionAra.set(i, optionAra.get(i).substring(3));
				}

				VideoQuestion videoQuestion = new VideoQuestion(question, optionAra, indexOfAnswer, fileName);
				array.add(videoQuestion);
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return array;
	}

	private ArrayList<Question> getSound(Connection connect, ArrayList<Question> array)
	{
		try
		{
			String query = "Select * from SoundQuestions";
			PreparedStatement pst = connect.prepareStatement(query);

			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String question = results.getString("Questions");
				String options = results.getString("Options");
				String answer = results.getString("Answers");
				String fileName = results.getString("File");

				ArrayList<String> optionAra = new ArrayList<String>(Arrays.asList(options.split("\n")));

				int indexOfAnswer = 0;

				for(int i = 0; i < optionAra.size(); i++)
				{
					if (optionAra.get(i).startsWith(answer.toLowerCase()))
						indexOfAnswer = i;

					optionAra.set(i, optionAra.get(i).substring(3));
				}

				SoundQuestion soundQuestion = new SoundQuestion(question, optionAra, indexOfAnswer, fileName);
				array.add(soundQuestion);
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return array;
	}

	public boolean checkAnswer(int chosenAnswer)
	{
		if (currentQuestion == null)
			return false;

		return currentQuestion.getCorrectIndex() == chosenAnswer;
	}

	public boolean checkShortAnswer(String answerTxt)
	{
		if (currentQuestion == null || !(currentQuestion instanceof ShortQuestion) )
			return false;

		boolean result = false;
		
		for(int i = 0; i < currentQuestion.getAnswers().size(); i++)
		{
			String keyword = currentQuestion.getAnswers().get(i);
			
			if(answerTxt.contains(keyword))
			{
				result = true;
				break;
			}
		}

		return result;
	}

	public Question getQuestion()
	{
		if (questions == null || questions.isEmpty())
			throw new NullPointerException("Questions ArrayList is empty");

		currentQuestion = questions.get(0);
		questions.remove(0);

		return currentQuestion;
	}
}
