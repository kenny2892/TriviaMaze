package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Database
{
	
	private ArrayList<Question> questions;
	
	public Database()
	{
		this.questions = createQuestionDatabase();
	}
	
	public ArrayList<Question> getQuestions()
	{
		return this.questions;
	}
	
	public ArrayList<Question> createQuestionDatabase() // TODO add switch and enum for different databases
	{
		ArrayList<Question> array = new ArrayList<Question>();
		
		try
		{
			Connection connect = JavaDatabaseConnection.dbConnector();
			
			array = getMultipleChoice(connect, array);
			array = getTrueFalse(connect, array);
			
			while(array.size() <= 40) // TODO For Demo Only! Remove later
			{
				array = getMultipleChoice(connect, array);
				array = getTrueFalse(connect, array);
			}
			
			Collections.shuffle(array);
			
			connect.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
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
					if(optionAra.get(i).startsWith(answer))
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
				if(answer.compareTo("False") == 0)
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

}
