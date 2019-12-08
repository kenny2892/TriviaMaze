package application.controllers;

import java.util.ArrayList;

import application.Main;
import application.SoundQuestion;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.shape.Rectangle;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

public class SoundQuestionController
{
	@FXML private Slider timeSlider;
	@FXML private ProgressBar timeProgressBar;
	@FXML private Slider volumeSlider;
	@FXML private ImageView playBtn;
	@FXML private ImageView volumeBtn;
	@FXML private Pane soundControlPane;
	
	@FXML private TextArea soundQuestionText;
	@FXML private Group mcGroupA;
	@FXML private Text mcTextA;
	@FXML private Group mcGroupB;
	@FXML private Text mcTextB;
	@FXML private Group mcGroupC;
	@FXML private Text mcTextC;
	@FXML private Group mcGroupD;
	@FXML private Text mcTextD;
	@FXML private Group mcGroupE;
	@FXML private Text mcTextE;
	@FXML private Group mcGroupF;
	@FXML private Text mcTextF;
	@FXML private Rectangle correctAnswer;
	
	@FXML private MediaView mp3View;
	private MediaPlayer mp3Player;
	
	public void setQuestion(SoundQuestion question)
	{
		mp3Player = new MediaPlayer( new Media(getClass().getResource("/resources/sounds/" + question.getFileName()).toExternalForm()));
		mp3View.setMediaPlayer(mp3Player);
		
		mp3Player.currentTimeProperty().addListener(new InvalidationListener()
		{
			@Override
			public void invalidated(Observable observable)
			{
				updatePlayer();
			}
		});
		
		timeSlider.valueProperty().addListener(new InvalidationListener()
		{
			@Override
			public void invalidated(Observable observable)
			{
				if(timeSlider.isPressed())
					mp3Player.seek(mp3Player.getMedia().getDuration().multiply(timeSlider.getValue() / 100));
			}
		});
		
		timeSlider.valueProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				double value = newValue.doubleValue() / 100;
				
				if(value < 0.3)
					value += 0.005;
				
				timeProgressBar.setProgress(value);
			}
		});
		
		volumeSlider.setMax(100);
		volumeSlider.setMin(0);
		volumeSlider.setValue(50);
		
		volumeSlider.valueProperty().addListener(new InvalidationListener()
		{
			@Override
			public void invalidated(Observable observable)
			{
				if(volumeSlider.isPressed())
					mp3Player.setVolume(volumeSlider.getValue() / 100);
			}
		});
		
		setUpText(question);
	}
	
	private void setUpText(SoundQuestion question)
	{
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
		
		int[] correctX = new int[] {309, 854, 1393};
		int[] correctY = new int[] {712, 847};
		
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
				correctAnswer.setLayoutX(correctX[2]);
				correctAnswer.setLayoutY(correctY[0]);
				break;

			case 3:
				correctAnswer.setLayoutX(correctX[0]);
				correctAnswer.setLayoutY(correctY[1]);
				break;

			case 4:
				correctAnswer.setLayoutX(correctX[1]);
				correctAnswer.setLayoutY(correctY[1]);
				break;

			case 5:
				correctAnswer.setLayoutX(correctX[2]);
				correctAnswer.setLayoutY(correctY[1]);
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
		playBtnSound();
		Main.checkAnswer(0);
	}

	public void selectmcB()
	{
		playBtnSound();
		Main.checkAnswer(1);
	}

	public void selectmcC()
	{
		playBtnSound();
		Main.checkAnswer(2);
	}

	public void selectmcD()
	{
		playBtnSound();
		Main.checkAnswer(3);
	}

	public void selectmcE()
	{
		playBtnSound();
		Main.checkAnswer(4);
	}

	public void selectmcF()
	{
		playBtnSound();
		Main.checkAnswer(5);
	}
	
	private void updatePlayer()
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				timeSlider.setValue((mp3Player.getCurrentTime().toMillis() / mp3Player.getTotalDuration().toMillis()) * 100);
			}
		});
	}
	
	public void playOrPause()
	{
		if(mp3Player == null)
			return;
		
		playBtnSound();
		
		if(mp3Player.getStatus() == Status.PLAYING)
		{
			mp3Player.pause();
			playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Play.png").toExternalForm()));
			return;
		}
		
		mp3Player.play();
		playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Pause.png").toExternalForm()));
	}
	
	public void skipBack()
	{
		if(mp3Player == null)
			return;

		playBtnSound();
		
		mp3Player.seek(mp3Player.getMedia().getDuration().multiply(0));
		mp3Player.pause();
		playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Play.png").toExternalForm()));
		timeSlider.setValue(0);
	}
	
	public void skipForward()
	{
		if(mp3Player == null)
			return;
		
		mp3Player.seek(mp3Player.getTotalDuration());
		timeSlider.setValue(100);
		
		mp3Player.pause();
		playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Play.png").toExternalForm()));
	}
	
	public void mute()
	{
		if(mp3Player == null)
			return;

		playBtnSound();
		
		if(mp3Player.isMute())
		{
			mp3Player.setMute(false);
			volumeBtn.setImage(new Image(this.getClass().getResource("/resources/images/Volume.png").toExternalForm()));
		}
		
		else
		{
			mp3Player.setMute(true);
			volumeBtn.setImage(new Image(this.getClass().getResource("/resources/images/Volume Mute.png").toExternalForm()));
		}
	}
	
	private void playBtnSound()
	{		
		Media soundFX = new Media(this.getClass().getResource("/resources/sounds/Button.mp3").toExternalForm());
		MediaPlayer player = new MediaPlayer(soundFX);
		player.play();
	}
}
