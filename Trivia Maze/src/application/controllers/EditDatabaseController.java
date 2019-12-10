package application.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DatabaseConnection;
import application.Main;
import application.enums.DatabaseType;
import application.enums.QuestionType;
import application.enums.SceneType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class EditDatabaseController
{
	@FXML private Pane addPane, removePane;
	@FXML private Text invalidInput, completedInput;
	@FXML private Spinner<Integer> removeIdSpinner;
	@FXML private TextFlow questionsTextFlow;
	@FXML private ComboBox<String> selectMode, selectDatabase, selectType;
	@FXML private TextArea questionTxt;
	@FXML private Group shortAnswer, trueFalse, mc, correctAnswer, fileNameGroup;
	@FXML private TextArea shortAnswerTxt;
	@FXML private TextArea mcATxt, mcBTxt, mcCTxt, mcDTxt, mcETxt, mcFTxt;
	@FXML private TextArea filename;
	@FXML private ComboBox<String> selectAnswer, trueFalseComboBox;

	private DatabaseType currDatabaseType;
	private QuestionType currQuestionType;

	public void initialize()
	{
		addPane.setVisible(false);
		removePane.setVisible(false);
		invalidInput.setVisible(false);
		completedInput.setVisible(false);

		ObservableList<String> modes = FXCollections.observableArrayList("Add", "Remove");
		ObservableList<String> databases = FXCollections.observableArrayList("Java", "Anime", "Video Games");
		ObservableList<String> questionTypes = FXCollections.observableArrayList("Multiple Choice", "True False", "Short",
				"Video", "Sound");

		selectMode.getSelectionModel().clearSelection();
		selectDatabase.getSelectionModel().clearSelection();
		selectType.getSelectionModel().clearSelection();

		selectMode.setItems(modes);
		selectDatabase.setItems(databases);
		selectType.setItems(questionTypes);
	}

	public void change()
	{
		invalidInput.setVisible(false);

		String mode = selectMode.getValue();
		String database = selectDatabase.getValue();
		String question = selectType.getValue();

		if (mode == null || database == null || question == null)
			return;

		DatabaseType databaseType = null;
		QuestionType questionType = null;

		try
		{
			databaseType = DatabaseType.valueOf(database.toUpperCase().replace(" ", "_"));
			questionType = QuestionType.valueOf(question.replace(" ", "_").toUpperCase());
		}

		catch(Exception e)
		{
			invalidInput.setVisible(true);
			e.printStackTrace();
			return;
		}

		if (databaseType == null || questionType == null)
		{
			invalidInput.setVisible(true);
			return;
		}

		else if (mode.compareTo("Add") == 0)
			add(questionType);

		else if (mode.compareTo("Remove") == 0)
			remove(databaseType, questionType);

		currDatabaseType = databaseType;
		currQuestionType = questionType;
		playBtnSound();
	}

	private void add(QuestionType questionType)
	{
		addPane.setVisible(true);
		removePane.setVisible(false);
		shortAnswer.setVisible(false);
		trueFalse.setVisible(false);
		mc.setVisible(false);
		fileNameGroup.setVisible(false);

		switch(questionType)
		{
			case MULTIPLE_CHOICE:
				mc.setVisible(true);
				break;

			case TRUE_FALSE:
				trueFalse.setVisible(true);
				break;

			case SHORT:
				shortAnswer.setVisible(true);
				break;

			case VIDEO:
				mc.setVisible(true);
				fileNameGroup.setVisible(true);
				break;

			case SOUND:
				mc.setVisible(true);
				fileNameGroup.setVisible(true);
				break;
		}

		if (questionType == QuestionType.MULTIPLE_CHOICE || questionType == QuestionType.SOUND
				|| questionType == QuestionType.VIDEO)
		{
			correctAnswer.setLayoutX(63);
			correctAnswer.setLayoutY(366);

			fileNameGroup.setLayoutX(1182);
			fileNameGroup.setLayoutY(450);
		}

		else
		{
			correctAnswer.setLayoutX(63);
			correctAnswer.setLayoutY(180);

			fileNameGroup.setLayoutX(1182);
			fileNameGroup.setLayoutY(264);
		}

		if (questionType == QuestionType.TRUE_FALSE || questionType == QuestionType.SHORT)
			correctAnswer.setVisible(false);

		ObservableList<String> letters = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F");
		selectAnswer.setItems(letters);

		ObservableList<String> tf = FXCollections.observableArrayList("True", "False");
		trueFalseComboBox.setItems(tf);

		trueFalseComboBox.setValue("True");
	}

	private void remove(DatabaseType databaseType, QuestionType questionType)
	{
		int max = 0;
		int min = 0;
		addPane.setVisible(false);
		removePane.setVisible(true);

		switch(questionType)
		{
			case MULTIPLE_CHOICE:
				max = showQuestion(databaseType, "Select * from MultipleChoiceQuestions");
				break;

			case TRUE_FALSE:
				max = showQuestion(databaseType, "Select * from TrueFalseQuestions");
				break;

			case SHORT:
				max = showQuestion(databaseType, "Select * from ShortAnswerQuestions");
				break;

			case VIDEO:
				max = showQuestion(databaseType, "Select * from VideoQuestions");
				break;

			case SOUND:
				max = showQuestion(databaseType, "Select * from SoundQuestions");
				break;
		}

		if (max != min)
			min = 1;

		removeIdSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, min));
	}

	private int showQuestion(DatabaseType databaseType, String query)
	{
		int totalQuestions = 0;

		questionsTextFlow.getChildren().clear();
		try
		{
			Connection connect = DatabaseConnection.dbConnector(databaseType);
			PreparedStatement pst = connect.prepareStatement(query);

			ResultSet results = pst.executeQuery();

			while(results.next())
			{
				String id = results.getString("ID");
				String question = results.getString("Questions");

				Text idTxt = new Text(id + ".");
				idTxt.setFont(Font.font("Arial", FontWeight.NORMAL, 35));
				idTxt.setFill(Color.WHITE);

				TextArea questionTxt = new TextArea(question);
				questionTxt.setPrefWidth(1780);
				questionTxt.setPrefHeight(108);
				questionTxt.setLayoutX(40);
				questionTxt.setLayoutY(-34);
				questionTxt.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
				questionTxt.setWrapText(true);

				Group group = new Group(idTxt, questionTxt);
				questionsTextFlow.getChildren().add(group);
				totalQuestions++;
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return totalQuestions;
	}

	public void addToDatabase()
	{
		invalidInput.setVisible(false);
		completedInput.setVisible(false);

		boolean result = false;
		try
		{
			switch(currQuestionType)
			{
				case MULTIPLE_CHOICE:
					result = addMC("Insert Into MultipleChoiceQuestions (Questions, Options, Answers) Values(?, ?, ?)");
					break;

				case SHORT:
					result = addShort("Insert Into ShortAnswerQuestions (Questions, Keywords) Values(?, ?)");
					break;

				case SOUND:
					result = addFile("Insert Into SoundQuestions (Questions, Options, Answers, File) Values(?, ?, ?, ?)");
					break;

				case VIDEO:
					result = addFile("Insert Into VideoQuestions (Questions, Options, Answers, File) Values(?, ?, ?, ?)");
					break;

				case TRUE_FALSE:
					result = addTF("Insert Into TrueFalseQuestions (Questions, Answers) Values(?, ?)");
					break;
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}

		if (result)
		{
			completedInput.setVisible(true);
			resetUI();
		}

		else
			invalidInput.setVisible(true);

		playBtnSound();
	}

	private boolean addMC(String query)
	{
		String question = questionTxt.getText();

		if (question.length() == 0)
			return false;

		String a = mcATxt.getText();
		String b = mcBTxt.getText();
		String c = mcCTxt.getText();
		String d = mcDTxt.getText();
		String e = mcETxt.getText();
		String f = mcFTxt.getText();

		if (f.length() != 0 && !(e.length() != 0 && d.length() != 0 && c.length() != 0 && b.length() != 0 && a.length() != 0))
			return false;

		else if (e.length() != 0 && !(d.length() != 0 && c.length() != 0 && b.length() != 0 && a.length() != 0))
			return false;

		else if (d.length() != 0 && !(c.length() != 0 && b.length() != 0 && a.length() != 0))
			return false;

		else if (c.length() != 0 && !(b.length() != 0 && a.length() != 0))
			return false;

		else if (b.length() != 0 && a.length() == 0)
			return false;

		else if (a.length() == 0)
			return false;

		String correct = selectAnswer.getValue();

		if (correct == null)
			return false;

		else if (correct.compareTo("a") == 0 && a.length() == 0)
			return false;

		else if (correct.compareTo("b") == 0 && b.length() == 0)
			return false;

		else if (correct.compareTo("c") == 0 && c.length() == 0)
			return false;

		else if (correct.compareTo("d") == 0 && d.length() == 0)
			return false;

		else if (correct.compareTo("e") == 0 && e.length() == 0)
			return false;

		else if (correct.compareTo("f") == 0 && f.length() == 0)
			return false;

		String options = getOptionsStr(a, b, c, d, e, f);

		try
		{
			Connection connect = DatabaseConnection.dbConnector(currDatabaseType);

			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, question);
			pst.setString(2, options);
			pst.setString(3, correct.toLowerCase());
			pst.executeUpdate();

			pst.close();
			connect.close();
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	private boolean addFile(String query)
	{
		String question = questionTxt.getText();

		if (question.length() == 0)
			return false;

		String a = mcATxt.getText();
		String b = mcBTxt.getText();
		String c = mcCTxt.getText();
		String d = mcDTxt.getText();
		String e = mcETxt.getText();
		String f = mcFTxt.getText();

		if (f.length() != 0 && !(e.length() != 0 && d.length() != 0 && c.length() != 0 && b.length() != 0 && a.length() != 0))
			return false;

		else if (e.length() != 0 && !(d.length() != 0 && c.length() != 0 && b.length() != 0 && a.length() != 0))
			return false;

		else if (d.length() != 0 && !(c.length() != 0 && b.length() != 0 && a.length() != 0))
			return false;

		else if (c.length() != 0 && !(b.length() != 0 && a.length() != 0))
			return false;

		else if (b.length() != 0 && a.length() == 0)
			return false;

		else if (a.length() == 0)
			return false;

		String correct = selectAnswer.getValue();

		if (correct == null)
			return false;

		else if (correct.compareTo("a") == 0 && a.length() == 0)
			return false;

		else if (correct.compareTo("b") == 0 && b.length() == 0)
			return false;

		else if (correct.compareTo("c") == 0 && c.length() == 0)
			return false;

		else if (correct.compareTo("d") == 0 && d.length() == 0)
			return false;

		else if (correct.compareTo("e") == 0 && e.length() == 0)
			return false;

		else if (correct.compareTo("f") == 0 && f.length() == 0)
			return false;

		String options = getOptionsStr(a, b, c, d, e, f);

		String file = filename.getText();

		if (file == null)
			return false;

		try
		{
			Connection connect = DatabaseConnection.dbConnector(currDatabaseType);

			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, question);
			pst.setString(2, options);
			pst.setString(3, correct.toLowerCase());
			pst.setString(4, file);
			pst.executeUpdate();

			pst.close();
			connect.close();
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	private String getOptionsStr(String a, String b, String c, String d, String e, String f)
	{
		String result = "";

		if (a == null || b == null || c == null || d == null || e == null || f == null)
			return result;

		else if (a.length() != 0)
		{
			result += "a. " + a;
			if (b.length() != 0)
			{
				result += "\n" + "b. " + b;
				if (c.length() != 0)
				{
					result += "\n" + "c. " + c;
					if (d.length() != 0)
					{
						result += "\n" + "d. " + d;
						if (e.length() != 0)
						{
							result += "\n" + "e. " + e;
							if (f.length() != 0)
							{
								result += "\n" + "f. " + f;
							}
						}
					}
				}
			}
		}

		return result;
	}

	private boolean addShort(String query)
	{
		String question = questionTxt.getText();
		String keywords = shortAnswerTxt.getText();

		if (question.length() == 0 || keywords.length() == 0)
			return false;

		try
		{
			Connection connect = DatabaseConnection.dbConnector(currDatabaseType);

			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, question);
			pst.setString(2, keywords);
			pst.executeUpdate();

			pst.close();
			connect.close();
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	private boolean addTF(String query)
	{
		String question = questionTxt.getText();
		String answer = trueFalseComboBox.getValue();

		if (question.length() == 0 || answer == null || answer.length() == 0)
			return false;

		try
		{
			Connection connect = DatabaseConnection.dbConnector(currDatabaseType);

			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, question);
			pst.setString(2, answer);
			pst.executeUpdate();

			pst.close();
			connect.close();
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;
	}

	public void removeFromDatabase()
	{
		invalidInput.setVisible(false);
		completedInput.setVisible(false);

		String query = "";

		switch(currQuestionType)
		{
			case MULTIPLE_CHOICE:
				query = "Delete From MultipleChoiceQuestions Where ID = ?";
				break;

			case SHORT:
				query = "Delete From ShortAnswerQuestions Where ID = ?";
				break;

			case SOUND:
				query = "Delete From SoundQuestions Where ID = ?";
				break;

			case TRUE_FALSE:
				query = "Delete From TrueFalseQuestions Where ID = ?";
				break;

			case VIDEO:
				query = "Delete From VideoQuestions Where ID = ?";
				break;
		}

		boolean result = false;
		
		try
		{
			Connection connect = DatabaseConnection.dbConnector(currDatabaseType);

			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, removeIdSpinner.getValue() + "");
			pst.executeUpdate();

			pst.close();
			connect.close();
			result = true;
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		if (result)
		{
			completedInput.setVisible(true);
			resetUI();
		}

		else
			invalidInput.setVisible(true);

		playBtnSound();
	}
	
	private void resetUI()
	{
		questionTxt.setText("");
		mcATxt.setText("");
		mcBTxt.setText("");
		mcCTxt.setText("");
		mcDTxt.setText("");
		mcETxt.setText("");
		mcFTxt.setText("");
		filename.setText("");
		selectAnswer.setValue("A");
	}

	public void backToMap()
	{
		playBtnSound();
		Main.changeScene(SceneType.MAP);
	}

	private void playBtnSound()
	{
		Media soundFX = new Media(this.getClass().getResource("/resources/sounds/Button.mp3").toExternalForm());
		MediaPlayer player = new MediaPlayer(soundFX);
		player.setVolume(Main.getVolume());
		player.play();
	}
}
