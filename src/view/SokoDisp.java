package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SokoDisp extends Canvas implements Initializable{

	private Character[][] maze;
	boolean isWin;
	Image win = null;
	Image wall = null;
	Image pitHole = null;
	Image bonePitHole = null;
	Image bone = null;
	Image floor = null;
	Image kinder = null;

	public SokoDisp() {
		isWin = false;
		try {
			wall = new Image(new FileInputStream("./resources/bush.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pitHole = new Image(new FileInputStream("./resources/pithole2.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bonePitHole = new Image(new FileInputStream("./resources/dog.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bone = new Image(new FileInputStream("./resources/b.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			floor = new Image(new FileInputStream("./resources/floor.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			kinder = new Image(new FileInputStream("./resources/Kinder.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			win = new Image(new FileInputStream("./resources/Win.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Image getImage(Character sign) {
		switch (sign) {
		case ' ':
			return floor;
		case '@':
			return bone;
		case 'o':
			return pitHole;
		case '#':
			return wall;
		case 'v':
			return bonePitHole;
		case 'A':
			return kinder;
		}
		return null;
	}

	public void setMaze(Character[][] maze) {
		this.maze = maze;
		this.setFocusTraversable(true);
		this.setFocused(true);
		redraw();
	}

	public void redraw() {
		if (maze != null) {

			GraphicsContext gc = this.getGraphicsContext2D();

			double W = getWidth();
			double H = getHeight();
			double w = W / maze[0].length;
			double h = H / maze.length;
			Image current = null;
			gc.clearRect(0, 0, W, H);
			if (!isWin) {
				for (int i = 0; i < maze.length; i++)
					for (int j = 0; j < maze[0].length; j++) {
						current = getImage(maze[i][j]);
						gc.drawImage(current, j * w, i * h, w, h);
					}
			} else {
				gc.clearRect(0, 0, W, H);
				gc.drawImage(win, 150, 0, 360, 640);
			}
		}
		this.setFocused(true);

	}

	public void drawWin() {
		isWin = true;
		redraw();
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
