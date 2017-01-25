
package Levels;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;

import CellTypes.Box;
import CellTypes.CellType;
import CellTypes.Floor;
import CellTypes.Goal;
import CellTypes.GoalBox;
import CellTypes.Hero;

/**
 * The Level Class Purpose is to be a data holder 
 * which means that she will have all the information of the level itself and 
 * will have the ability to change the data itself.
 * 
 */
public class Level implements Serializable {
	private String levelName;
	private int difficulty;
	private Point CharacterPosition;
	private HashMap<Point, CellType> BoardObjects;
	private CellType[][] LevelBoard;
	private int maxX;
	private int maxY;

	/**
	 * Class constructor must be given parameters.
	 * @param levelName Desired name to set for the level.
	 * @param difficulty
	 * @param characterPosition
	 * @param boardObjects
	 * @param levelBoard
	 * @param maxX
	 * @param maxY
	 * 
	 */

	public Level(String levelName, int difficulty, Point characterPosition, HashMap<Point, CellType> boardObjects,
			CellType[][] levelBoard, int maxX, int maxY) {
		super();
		this.levelName = levelName;
		this.difficulty = difficulty;
		CharacterPosition = characterPosition;
		BoardObjects = boardObjects;
		LevelBoard = levelBoard;
		this.maxX = maxX;    
		this.maxY = maxY;
	}
	/**
	 * default constructor 
	 */
	public Level() {
		this.levelName = null;
		this.difficulty = -1;
		this.BoardObjects = new HashMap<Point, CellType>();
		// this.ConutObjects = new HashMap<String, Integer>();
		// ConutObjects.put(Box.class.getSimpleName(), 0);
		// ConutObjects.put(Goal.class.getSimpleName(), 0);
		// ConutObjects.put(Wall.class.getSimpleName(), 0);
		// ConutObjects.put(GoalBox.class.getSimpleName(), 0);
		// ConutObjects.put(Hero.class.getSimpleName(), 0);
		LevelBoard = null;
		this.setCharacterPosition(null);

	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public HashMap<Point, CellType> getBoardObjects() {
		return BoardObjects;
	}

	public void setBoardObjects(HashMap<Point, CellType> boardObjects) {
		BoardObjects = boardObjects;
	}

	public CellType[][] getLevelBoard() {
		return LevelBoard;
	}

	public void setLevelBoard(CellType[][] levelBoard) {
		LevelBoard = levelBoard;
	}
	/**
	 * 
	 * @return the max value of the of the objects array 
	 */
	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	/**
	 * 
	 * @return the character position
	 */
	public Point getCharacterPosition() {
		return CharacterPosition;
	}
	public void setCharacterPosition(Point characterPosition) {
		CharacterPosition = characterPosition;

	}
	/**
	 * Put Cell type into a point
	 * @param point the position of the object on the board		
	 * @param c the cell type
	 */
	public void createCell(Point point, CellType c) {
		this.BoardObjects.put(point, c);
		// this.ConutObjects.put(c.getClass().getSimpleName(),
		// ConutObjects.get(c)+1);
	}
	
	/**
	 * the same as createCell method, but with x and y values
	 * @param x
	 * @param y
	 * @param c
	 */

	public void createCell(int x, int y, CellType c) {
		Point point = new Point(x, y);
		this.BoardObjects.put(point, c);
		// this.ConutObjects.put(c.getClass().getSimpleName(),
		// ConutObjects.get(c)+1);
	}

	/** 
	 * creating the board with the data on the hash map
	 */
	public void createBoard() {
		if (this.maxX != -1 && this.maxY != -1) {
			this.LevelBoard = new CellType[maxY][maxX];
			for (int i = 0; i < maxY; i++)
				for (int j = 0; j < maxX; j++)
					this.LevelBoard[i][j] = new Floor();

		}
	}
	/**
	 * Will Refresh the Object array with the Board Object hashMap */
	public void refreshBoard() {
		for (Point p : this.BoardObjects.keySet()) {
			this.LevelBoard[(int) p.getY()][(int) p.getX()] = this.BoardObjects.get(p);
		}
		this.LevelBoard[(int) getCharacterPosition().getY()][(int) getCharacterPosition().getX()] = new Hero();
	}
	
	/**
	 * moving the cell types on the board by changing the values at the key points.
	 * @param p the origin point 
	 * @param pNext the destination point 
	 */
	public void move(Point p, Point pNext) {
		if (this.BoardObjects.get(p) instanceof GoalBox) { 			//Pushing GoalBox
			if (this.BoardObjects.get(pNext) instanceof Goal) {		//To Goal
				this.BoardObjects.put(pNext, new GoalBox());
				this.BoardObjects.put(p, new Goal());
				this.setCharacterPosition(p);
			} else {												//To Floor
				this.BoardObjects.put(pNext, new Box());
				this.BoardObjects.put(p, new Goal());
				this.setCharacterPosition(p);
			}														//Pushing Box
		} else if (this.BoardObjects.get(pNext) instanceof Goal){    //To Goal
			this.BoardObjects.put(pNext, new GoalBox());
			this.BoardObjects.put(p, new Floor());
			this.setCharacterPosition(p);
		}else{
			this.BoardObjects.put(pNext, this.BoardObjects.get(p));	//To Floor
			this.BoardObjects.put(p, new Floor());
			this.setCharacterPosition(p);
		}
	}
	
	/**
	 * Will get a point and will return true if it is the character position**/ 
	public boolean isCharacterPosition(int x, int y) {
		return getCharacterPosition().equals(new Point(x, y));
	}
	
	/** 
	 * Returns true if there are no boxes  on the board (end of game)
	 * @return true if there are no boxes
	 *  */
	public boolean isFinished(){
		for(Point p:BoardObjects.keySet()){
			if(this.BoardObjects.get(p).getType() instanceof Goal) return false;
		}
		return true;
	}

}
