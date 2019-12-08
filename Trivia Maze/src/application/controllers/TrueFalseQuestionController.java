package application.controllers;

import application.Main;
import application.TrueFalseQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

public class TrueFalseQuestionController
{
	@FXML private TextArea tfQuestionText;
	@FXML private Rectangle correctAnswer;
	
	public void selectTrue()
	{
		playBtnSound();
		Main.checkAnswer(0);
	}

	public void selectFalse()
	{
		playBtnSound();
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
	
	private void playBtnSound()
	{		
		Media soundFX = new Media(this.getClass().getResource("/resources/sounds/Button.mp3").toExternalForm());
		MediaPlayer player = new MediaPlayer(soundFX);
		player.play();
	}
}
