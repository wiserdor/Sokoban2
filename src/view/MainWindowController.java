package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowController extends Observable implements View {

	@FXML
	SokoDisp sokoDisp;
	@FXML
	Label stepsLbl;
	@FXML
	Label timerLbl;
	AnimationTimer clock;
	private int steps;
	boolean timerOn;
	boolean win;
	private Media startMp3 = new Media(new File("./resources/start1.mp3").toURI().toString());
	private Media winMp3 = new Media(new File("./resources/win.mp3").toURI().toString());
	private MediaPlayer player = new MediaPlayer(startMp3);
	private MediaPlayer finished = new MediaPlayer(winMp3);

	public void startTimer() {
		timerOn = true;
		clock = new AnimationTimer() {
			private long timestamp;
			private long time = 0;
			private long fraction = 0;

			@Override
			public void start() {
				// current time adjusted by remaining time from last run
				timestamp = System.currentTimeMillis() - fraction;

			}

			@Override
			public void stop() {
				// save leftover time not handled with the last update
				fraction = System.currentTimeMillis() - timestamp;
			}

			@Override
			public void handle(long now) {
				long newTime = System.currentTimeMillis();
				if (timestamp + 1000 <= newTime) {
					long deltaT = (newTime - timestamp) / 1000;
					time += deltaT;
					timestamp += 1000 * deltaT;
					timerLbl.setText(Long.toString(time));
				}
			}
		};
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				clock.start();
			}
		});
	}

	public void stopTimer() {
		timerOn = false;
		clock.stop();
	}

	public MainWindowController() {
		win = false;

		sokoDisp = new SokoDisp();
		timerOn = false;

	}

	public void start() {
		String command = "Display";
		String[] s = new String[1];
		stepsLbl.setText("0");
		this.startTimer();
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
			this.player.stop();
			this.finished.play();
			win = true;
			sokoDisp.drawWin();

		}
	}

}
