package model.IO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import model.CellTypes.CellType;
import model.Levels.Level;

public class MyTextLevelSaver implements LevelSaver {
	/**
	 * @return level type as string
	 */
	@Override
	public String getFileTypeString() {
		// TODO Auto-generated method stub
		return "txt";
	}
	/**
	 * the method saves the level object into an .txt file
	 * @param l the level that it needs to save
	 * @param file gets an output stream includes the file path
	 * 
	 */
	@Override
	public void saveLevel(Level l, OutputStream file) throws FileNotFoundException, IOException {

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(file));
		writer.write("LevelName " + l.getLevelName());
		writer.newLine();
		writer.write("Difficulty " + l.getDifficulty());
		writer.newLine();
		writer.write("LevelBoard");
		writer.newLine();
		saveBoard(l, writer);
		writer.close();
	}
	/** 
	 * exporting the board of a level into the given output stream
	 * @param l the level object
	 * @param writer the stream that we want to write to
	 * @throws IOException 
	 */
	public void saveBoard(Level l, BufferedWriter writer) throws IOException {
		CellType[][] arr = l.getLevelBoard();
		CellValueCreator cvc = new CellValueCreator();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (l.isCharacterPosition(j, i))
					writer.write('A');
				else
					writer.write(cvc.CreateValue(arr[i][j].getClass().toString()));
			}
			writer.newLine();
		}
		
	}

}
