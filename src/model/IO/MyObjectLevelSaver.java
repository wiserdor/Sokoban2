package model.IO;
/**
 * Class save object type files
 * @see LevelSaver
 */
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.Levels.Level;

public class MyObjectLevelSaver implements LevelSaver {
	/**
	 * @return level type as string
	 */
	public String getFileTypeString() {
		return "obj";
	}
	/**
	 * the method saves the level object into an .obj file
	 * @param l the level that it needs to save
	 * @param file gets an output stream includes the file path
	 * 
	 */
	@Override
	public void saveLevel(Level l, OutputStream file) throws IOException {

		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(file));
		oos.writeObject(l);
		oos.close();

	}

}
