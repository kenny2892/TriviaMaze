package application.controllers;

import java.util.ArrayList;

import application.Main;
import application.MultipleChoiceQuestion;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;

public class MultipleChoiceQuestionController
{
	@FXML private TextArea mcQuestionText;
	@FXML private Group mcGroupA;
	@FXML private TextArea mcTextA;
	@FXML private Group mcGroupB;
	@FXML private TextArea mcTextB;
	@FXML private Group mcGroupC;
	@FXML private TextArea mcTextC;
	@FXML private Group mcGroupD;
	@FXML private TextArea mcTextD;
	@FXML private Group mcGroupE;
	@FXML private TextArea mcTextE;
	@FXML private Group mcGroupF;
	@FXML private TextArea mcTextF;
	@FXML private Rectangle correctAnswer;
	
	public void setQuestion(MultipleChoiceQuestion question)
	{
		mcQuestionText.setText(question.getQuestion());
		ArrayList<String> answers = question.getAnswers();

		hideAllAnswers();

		switch(answers.size())
		{
			case 6:
				mcGroupF.setVisible(true);
				mcTextF.setText(answers.get(5));

			case 5:
				mcGroupE.setVisible(true);
				mcTextE.setText(answers.get(4));

			case 4:
				mcGroupD.setVisible(true);
				mcTextD.setText(answers.get(3));

			case 3:
				mcGroupC.setVisible(true);
				mcTextC.setText(answers.get(2));

			case 2:
				mcGroupB.setVisible(true);
				mcTextB.setText(answers.get(1));

			case 1:
				mcGroupA.setVisible(true);
				mcTextA.setText(answers.get(0));
		}
		
		int[] correctX = new int[] {212, 1018};
		int[] correctY = new int[] {385, 558, 720};
		
		switch(question.getCorrectIndex())
		{
			case 0:
				correctAnswer.setLayoutX(correctX[0]);
				correctAnswer.setLayoutY(correctY[0]);
				break;

			case 1:
				correctAnswer.setLayoutX(correctX[1]);
				correctAnswer.setLayoutY(correctY[0]);
				break;

			case 2:
				correctAnswer.setLayoutX(correctX[0]);
				correctAnswer.setLayoutY(correctY[1]);
				break;

			case 3:
				correctAnswer.setLayoutX(correctX[1]);
				correctAnswer.setLayoutY(correctY[1]);
				break;

			case 4:
				correctAnswer.setLayoutX(correctX[0]);
				correctAnswer.setLayoutY(correctY[2]);
				break;

			case 5:
				correctAnswer.setLayoutX(correctX[1]);
				correctAnswer.setLayoutY(correctY[2]);
				break;
		}
		
		if(Main.isCheatAnswer())
			correctAnswer.setVisible(true);
		
		else
			correctAnswer.setVisible(false);
	}

	private void hideAllAnswers()
	{
		mcGroupA.setVisible(false);
		mcGroupB.setVisible(false);
		mcGroupC.setVisible(false);
		mcGroupD.setVisible(false);
		mcGroupE.setVisible(false);
		mcGroupF.setVisible(false);
	}

	public void selectmcA()
	{
		Main.checkAnswer(0);
	}

	public void selectmcB()
	{
		Main.checkAnswer(1);
	}

	public void selectmcC()
	{
		Main.checkAnswer(2);
	}

	public void selectmcD()
	{
		Main.checkAnswer(3);
	}

	public void selectmcE()
	{
		Main.checkAnswer(4);
	}

	public void selectmcF()
	{
		Main.checkAnswer(5);
	}
}
