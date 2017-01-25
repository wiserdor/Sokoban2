package IO;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import Levels.Level;

public class MyXmlLevelLoader implements LevelLoader {
	/**
	 * the method load data from .XML file into a new Level object
	 * @param file gets an input stream includes the file path
	 * @return the level after it loads the data into it
	 */
	@Override
	public Level loadLevel(InputStream file) throws FileNotFoundException, IOException {
		Level l;
		XMLDecoder xis = new XMLDecoder(new BufferedInputStream(file));
		l = (Level) xis.readObject();
		xis.close();
		return l;
	}
	/**
	 * @return level type as string
	 */
	@Override
	public String getFileTypeString() {
		return "xml";
	}

}
