package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowController extends Observable implements View {

	@FXML
	SokoDisp sokoDisp;
	@FXML
	Label stepsLbl;
	@FXML
	Text timerLbl;

	private Double counter = new Double(0);
	private StringProperty countString; // timer string
	private boolean countFlag = true; // responsible for timer stoping
	private boolean timerFlag = false; // timer is on
	private Timer timerThread = new Timer();; // timer

	private int steps;
	boolean win;
	private Media startMp3 = new Media(new File("./resources/start1.mp3").toURI().toString());
	private Media winMp3 = new Media(new File("./resources/win.mp3").toURI().toString());
	private MediaPlayer player = new MediaPlayer(startMp3);
	private MediaPlayer finished = new MediaPlayer(winMp3);

	public MainWindowController() {
		win = false;
		
		sokoDisp = new SokoDisp();
		

	}

	public void resetTimer() { // reset the game timer
		if (timerFlag) {
			counter = 0.0; // reset timer
			countFlag = true; // return the counting
		} else {
			timerThread.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					counter += 0.1;

					counter = BigDecimal.valueOf(counter).setScale(3, RoundingMode.HALF_UP).doubleValue();
					if (countFlag)
						countString.set("Timer: " + counter);
				}
			}, 0, 100);
			timerFlag = true;
		}
	}

	public void start() {
		String command = "Display";
		String[] s = new String[1];
		stepsLbl.setText("0");
		countString = new SimpleStringProperty("0");
		timerLbl.textProperty().bind(countString);
		timerLbl.setVisible(true);
		s[0] = command;
		this.setChanged();
		this.notifyObservers(s);
		sokoDisp.requestFocus();
		sokoDisp.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String direction = "";
				if (event.getCode() == KeyCode.LEFT) {
					direction = "left";
				} else if (event.getCode() == KeyCode.RIGHT) {
					direction = "right";
				} else if (event.getCode() == KeyCode.UP) {
					direction = "up";
				} else if (event.getCode() == KeyCode.DOWN) {
					direction = "down";
				}
				String command = "Move";
				String[] s = new String[2];
				s[0] = command;
				s[1] = direction;
				setChanged();
				notifyObservers(s);

			}
		});
		this.setChanged();
		this.notifyObservers(s);

	}

	public void stop() {
		this.player.stop();
		this.finished.stop();
		this.sokoDisp.setDisable(true);
	}

	public void openFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Load maze file");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			sokoDisp.setDisable(false);
			if (win == true) {
				sokoDisp.isWin = false;
				this.finished.stop();
				win = false;
			}
			String command = "Load";
			String[] s = new String[2];
			s[0] = command;
			s[1] = chosen.getAbsolutePath();
			player.play();
			setChanged();
			notifyObservers(s);
			this.resetTimer();
			this.start();

		}
	}

	public void saveFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save maze file");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen = fc.showSaveDialog(null);
		if (chosen != null) {
			String command = "Save";
			String[] s = new String[2];
			s[0] = command;
			s[1] = chosen.getAbsolutePath();
			setChanged();
			notifyObservers(s);

		}
	}

	public void exit() {
		String[] command = new String[2];
		command[0] = "exit";
		setChanged();
		notifyObservers(command);

	}

	public void exitGui() {
		Platform.exit();
	}

	@Override
	public void display(Character[][] board) {
		sokoDisp.setMaze(board);
	}

	@Override
	public void display(Character[][] board, int steps) {
		this.steps = steps;
		sokoDisp.setMaze(board);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stepsLbl.setText(Integer.toString(steps));
			}
		});
	}

	@Override
	public void setWin() throws FileNotFoundException {
		if (win == false) {
			this.countFlag = false;
			this.player.stop();
			this.finished.play();
			win = true;
			sokoDisp.drawWin();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to submit your score?", ButtonType.YES,
							ButtonType.NO);
					alert.showAndWait();

					if (alert.getResult() == ButtonType.YES) {
						getLeaderBoard();
					}
				}
			});
		}
	}

	public void getLeaderBoard() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					FXMLLoader fl = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
					Parent root1 = (Parent) fl.load();
					Stage stage = new Stage();
					stage.setTitle("Leaders");
					stage.setScene(new Scene(root1));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("can't open window");
				}
			}
		});

	}
	@Override
	public Double getFinishTime() {
		if(countFlag)
			return counter;
		return 0.0;
	}

}
