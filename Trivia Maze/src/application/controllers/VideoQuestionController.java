package application.controllers;

import application.VideoQuestion;
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

public class VideoQuestionController
{
	@FXML private Slider timeSlider;
	@FXML private ProgressBar timeProgressBar;
	@FXML private Slider volumeSlider;
	@FXML private ImageView playBtn;
	@FXML private ImageView volumeBtn;
	@FXML private Pane videoControlPane;
	
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
		
		mp4Player.stop();
		timeSlider.setValue(0);
		
		mp4Player.play();
		playBtn.setImage(new Image(this.getClass().getResource("/resources/images/Pause.png").toExternalForm()));
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
