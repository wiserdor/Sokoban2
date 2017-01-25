package model.IO;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import model.Levels.Level;

/**
 * Class load object type files
 *@see LevelLoader
 */
public class MyObjectLevelLoader implements LevelLoader {
	
	@Override
	/**
	 * the method load data from .obj file into a new Level object
	 * @param file gets an input stream includes the file path
	 * @return the level after it loads the data into it
	 */
	public Level loadLevel(InputStream file) throws ClassNotFoundException, IOException {
		Level l;
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(file));
		l = (Level) ois.readObject();
		ois.close();
		return l;
	}
	/**
	 * @return level type as string
	 */
	@Override
	public String getFileTypeString() {
		return "obj";
	}

}
