package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

public class MainWindowController extends Observable  implements View ,Initializable{
	
	@FXML
	SokoDisp sokoDisp;
	boolean win;
	private Media startMp3=new Media(new File("./resources/start1.mp3").toURI().toString());
	private Media winMp3=new Media(new File("./resources/win.mp3").toURI().toString());
	private MediaPlayer player=new MediaPlayer(startMp3);
	private MediaPlayer finished=new MediaPlayer(winMp3);

	public MainWindowController () 
	{
		win=false;
		sokoDisp = new SokoDisp();
		
	}
	
	public void start (){
		String command = "Display";
		String[] s = new String[1];
		s[0] = command;
		this.setChanged();
		this.notifyObservers(s);
		sokoDisp.requestFocus();
		sokoDisp.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String direction = "";
				System.out.println("inside");
				if (event.getCode() == KeyCode.LEFT) {
					direction = "left";					
	            }
				else if(event.getCode() == KeyCode.RIGHT) {
					direction = "right";
	            }
				else if(event.getCode() == KeyCode.UP) {
					direction = "up";
	            }
				else if(event.getCode() == KeyCode.DOWN) {
					direction = "down";
	            }
					
				String command = "Move";
				String[] s = new String[2];
				s[0] = command;
				s[1] = direction;
				//sokoDisp.redraw();
				setChanged();
				notifyObservers(s);
				
				
			}
		});		
		this.setChanged();
		this.notifyObservers(s);
		
	}
	public void stop (){
		this.player.stop();
		this.finished.stop();
		this.sokoDisp.setVisible(false);
	}
	public void openFile(){
		FileChooser fc= new FileChooser();
		fc.setTitle("Load maze file");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen =fc.showOpenDialog(null);
		if (chosen!=null){
			if(sokoDisp.isDisable()){
				sokoDisp.setDisable(false);
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
	public void saveFile(){
		FileChooser fc= new FileChooser();
		fc.setTitle("Save maze file");
		fc.setInitialDirectory(new File("./Levels"));
		File chosen =fc.showSaveDialog(null);
		if (chosen!=null){
			String command = "Save";
			String[] s = new String[2];
			s[0] = command;
			s[1] = chosen.getAbsolutePath();
			setChanged();
			notifyObservers(s);
			
		}
	}
	public void exit(){
		String[] command =new String[2];
		command[0]="exit";
		setChanged();
		notifyObservers(command);
	}

	@Override
	public void display(Character[][] board) {
		// TODO Auto-generated method stub
		sokoDisp.setMaze(board);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWin() throws FileNotFoundException {
		if(win==false){
			this.player.stop();
			this.finished.play();
			win=true;
			sokoDisp.drawWin();
			sokoDisp.setDisable(true);
			
			
			
		}
	}

	
}
