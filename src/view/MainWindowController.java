package view;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

public class MainWindowController extends Observable  implements View ,Initializable{
	
	@FXML
	SokoDisp sokoDisp;
	boolean win;
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
	 public static void playSound(String fileName){
		 	
	        Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + fileName);
	        MediaPlayer player = new MediaPlayer(m);
	        player.play();
	    }
	public void openFile(){
		FileChooser fc= new FileChooser();
		fc.setTitle("open maze file");
		fc.setInitialDirectory(new File("./resources"));
		File chosen =fc.showOpenDialog(null);
		if (chosen!=null){
			String command = "Load";
			String[] s = new String[2];
			s[0] = command;
			s[1] = chosen.getAbsolutePath();
			setChanged();
			notifyObservers(s);
		}
	}

	@Override
	public void displayError(String msg) {
		// TODO Auto-generated method stub
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
	public void setWin() {
		if(win==false){
			this.playSound("/resources/win.mp3");
			win=true;
		}
	}

	
}
