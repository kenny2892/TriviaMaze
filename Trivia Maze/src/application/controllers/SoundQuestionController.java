package application.controllers;

import application.SoundQuestion;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

public class SoundQuestionController
{
	@FXML private Slider timeSlider;
	@FXML private ProgressBar timeProgressBar;
	@FXML private Slider volumeSlider;
	@FXML private ImageView playBtn;
	@FXML private ImageView volumeBtn;
	@FXML private Pane soundControlPane;
	
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
		
//		soundControlPane.setVisible(false);
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
}
