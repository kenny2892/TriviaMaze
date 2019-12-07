package application.controllers;

import application.Main;
import application.TrueFalseQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;

public class TrueFalseQuestionController
{
	@FXML private TextArea tfQuestionText;
	@FXML private Rectangle correctAnswer;
	
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
		
		switch(question.getCorrectIndex())
		{
			case 0:
				correctAnswer.setLayoutX(508);
				correctAnswer.setLayoutY(537);
				break;

			case 1:
				correctAnswer.setLayoutX(1130);
				correctAnswer.setLayoutY(537);
				break;
		}
		
		if(Main.isCheatAnswer())
			correctAnswer.setVisible(true);
		
		else
			correctAnswer.setVisible(false);
	}
}
