package model.IO;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.CellTypes.Box;
import model.CellTypes.CellType;
import model.CellTypes.Floor;
import model.CellTypes.Goal;
import model.CellTypes.GoalBox;
import model.CellTypes.Wall;
import model.Levels.Level;



public class MyTextLevelLoader implements LevelLoader {
	/**
	 * the method load data from .txt file into a new Level object
	 * @param file gets an input stream includes the file path
	 * @return the level after it loads the data into it
	 */
	@Override
	public Level loadLevel(InputStream file) throws IOException {
		Level l = new Level();
		Scanner myScanner = null;
		ArrayList<String> lineArray = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		String line = reader.readLine();
		while (line != null) {			
			if (line != null)	
				lineArray.add(line);
			line = reader.readLine();
		}
		reader.close();
		boolean end = false;
		for (int i = 0; i < lineArray.size() && end == false; i++) {
			myScanner = new Scanner(lineArray.get(i));
			String lvlTmp = "";
			switch (myScanner.next()) {
			case "LevelName":
				while (myScanner.hasNext())
					lvlTmp = lvlTmp + myScanner.next();
				l.setLevelName(lvlTmp);
				lvlTmp = "";
				break;
			case "Difficulty":
				l.setDifficulty(myScanner.nextInt());
				break;
			case "LevelBoard":
				createBoard(l, lineArray.subList(i + 1, lineArray.size()));
				end = true;
				break;

			default:
				break;
			}
		}
		l.createBoard();
		l.refreshBoard();
		return l;
	}
	/**
	 * the method load the level board from a string list
	 * @param l the level that we want to load board into
	 * @param list the board info as string list
	 */
	public void createBoard(Level l, List<String> list) {
		int x;
		int y;
		int maxX = -1;
		l.setMaxY(list.size());
		for (y = 0; y < list.size(); y++) {
			for (x = 0; x < list.get(y).length(); x++) {
				if (maxX < x)
					maxX = x;
				switch (list.get(y).charAt(x)) {
				case '@':
					CellType box = new Box();
					l.createCell(x, y, box);
					break;
				case '#':
					CellType wall = new Wall();
					l.createCell(x, y, wall);
					break;
				case 'o':
					CellType goal = new Goal();
					l.createCell(x, y, goal);
					break;
				case 'v':
					CellType goalBox = new GoalBox();
					l.createCell(x, y, goalBox);
					break;
				case 'A':
					l.setCharacterPosition(new Point(x, y));
				case ' ':
					CellType floor = new Floor();
					l.createCell(x, y, floor);
					break;

				default:
					break;
				}
			}

		}
		l.setMaxX(maxX + 1);

	}
	/**
	 * @return level type as string
	 */
	@Override
	public String getFileTypeString() {
		return "txt";
	}

}
