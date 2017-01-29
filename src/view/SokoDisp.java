package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
		this.setFocusTraversable(true);
		this.setFocused(true);
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
	public void drawWin(){
		GraphicsContext gc = this.getGraphicsContext2D();
	
		gc.clearRect(0, 0, 600, 600);
		gc.fillRect(0, 0, 600, 600);
		gc.drawImage(this.kinder, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		this.setFocused(true);
	}
	
}
