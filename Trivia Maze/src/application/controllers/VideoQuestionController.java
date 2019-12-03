package application.controllers;

import java.util.ArrayList;

import application.Main;
import application.VideoQuestion;
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
import javafx.scene.text.Text;
import javafx.scene.media.MediaView;

public class VideoQuestionController
{
	@FXML private Slider timeSlider;
	@FXML private ProgressBar timeProgressBar;
	@FXML private Slider volumeSlider;
	@FXML private ImageView playBtn;
	@FXML private ImageView volumeBtn;
	@FXML private Pane videoControlPane;
	
	@FXML private TextArea videoQuestionText;
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
	
	@FXML private MediaView mp4View;
	private MediaPlayer mp4Player;
	
	public void setQuestion(VideoQuestion question)
	{
		mp4Player = new MediaPlayer( new Media(getClass().getResource("/resources/videos/" + question.getFileName()).toExternalForm()));
		mp4View.setMediaPlayer(mp4Player);
		
		mp4Player.currentTimeProperty().addListener(new InvalidationListener()
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
					mp4Player.seek(mp4Player.getMedia().getDuration().multiply(timeSlider.getValue() / 100));
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
					mp4Player.setVolume(volumeSlider.getValue() / 100);
			}
		});
		
		videoControlPane.setVisible(false);
		
		setUpText(question);
	}
	
	private void setUpText(VideoQuestion question)
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
	
	private void updatePlayer()
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				timeSlider.setValue((mp4Player.getCurrentTime().toMillis() / mp4Player.getTotalDuration().toMillis()) * 100);
			}
		});
	}
	
	public void playOrPause()
	{
		if(mp4Player == null)
			return;
		
		if(mp4Player.getStatus() == Status.PLAYING)
		{
			mp4Player.pause();
			playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Play.png").toExternalForm()));
			return;
		}
		
		mp4Player.play();
		playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Pause.png").toExternalForm()));
	}
	
	public void skipBack()
	{
		if(mp4Player == null)
			return;
		
		mp4Player.seek(mp4Player.getMedia().getDuration().multiply(0));
		mp4Player.pause();
		playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Play.png").toExternalForm()));
		timeSlider.setValue(0);
	}
	
	public void skipForward()
	{
		if(mp4Player == null)
			return;
		
		mp4Player.seek(mp4Player.getTotalDuration());
		timeSlider.setValue(100);
		
		mp4Player.pause();
		playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Play.png").toExternalForm()));
	}
	
	public void mute()
	{
		if(mp4Player == null)
			return;
		
		if(mp4Player.isMute())
		{
			mp4Player.setMute(false);
			volumeBtn.setImage(new Image(this.getClass().getResource("/resources/images/Volume.png").toExternalForm()));
		}
		
		else
		{
			mp4Player.setMute(true);
			volumeBtn.setImage(new Image(this.getClass().getResource("/resources/images/Volume Mute.png").toExternalForm()));
		}
	}
	
	public void enterPlayer()
	{
		if(mp4Player == null)
			return;
		
		videoControlPane.setVisible(true);
	}
	
	public void exitPlayer()
	{
		if(mp4Player == null)
			return;
		
		videoControlPane.setVisible(false);
	}
}
