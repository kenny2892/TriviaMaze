package application.controllers;

import application.Main;
import application.TrueFalseQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TrueFalseQuestionController
{
	@FXML private TextArea tfQuestionText;
	
	public void selectTrue()
	{
		Main.checkAnswer(0);
	}

	public void selectFalse()
	{
		Main.checkAnswer(1);
	}
	
	public void setQuestion(TrueFalseQuestion question)
	{
		tfQuestionText.setText(question.getQuestion());
	}
}
