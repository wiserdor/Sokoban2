package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SokoDisp extends Canvas{
	
	private Character[][] maze;
	
	Image wall = null;
	Image pitHole = null;
	Image bonePitHole = null;
	Image bone = null;
	Image floor = null;
	Image kinder = null;
	
	public SokoDisp()
	{
		try {
			wall = new Image(new FileInputStream("./resources/wall.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pitHole = new Image(new FileInputStream("./resources/pitHole.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bonePitHole = new Image(new FileInputStream("./resources/bonePitHole.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bone = new Image(new FileInputStream("./resources/bone.png"));
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
			kinder = new Image(new FileInputStream("./resources/kinder.jpeg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Image getImage(Character sign)
	{	
		switch (sign)
		{
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
		redraw();
	}
	
	public void redraw(){
		if (maze != null)
		{
			GraphicsContext gc = this.getGraphicsContext2D();
			
			double W = getWidth();
			double H = getHeight();
			double w = W/maze[0].length;
			double h = H/maze.length;
			Image current = null;
			gc.clearRect(0, 0, W, H);
			for(int i=0;i<maze.length;i++)
				for(int j=0;j<maze[0].length;j++)
				{
					current = getImage(maze[i][j]);
					gc.drawImage(current, j*w, i*h, w, h);
				}
		}
		this.setFocused(true);
	}
}
