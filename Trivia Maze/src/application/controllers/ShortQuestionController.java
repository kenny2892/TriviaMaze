package application.controllers;

import application.Main;
import application.ShortQuestion;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ShortQuestionController
{
	@FXML private TextArea shortQuestionText, shortAnswerText, keywordText;
	@FXML private Group correctAnswer;
	
	public void submit()
	{
		playBtnSound();
		Main.checkShortAnswer(shortAnswerText.getText());
	}
	
	public void setQuestion(ShortQuestion question)
	{
		shortQuestionText.setText(question.getQuestion());
		
		String keywords = "";
		for(String keyword : question.getAnswers())
			keywords += keyword + "\n";
		
		keywordText.setText(keywords);
		
		if(Main.isCheatAnswer())
			correctAnswer.setVisible(true);
		
		else
			correctAnswer.setVisible(false);
	}
	
	private void playBtnSound()
	{		
		Media soundFX = new Media(this.getClass().getResource("/resources/sounds/Button.mp3").toExternalForm());
		MediaPlayer player = new MediaPlayer(soundFX);
		player.setVolume(Main.getVolume());
		player.play();
	}
}
