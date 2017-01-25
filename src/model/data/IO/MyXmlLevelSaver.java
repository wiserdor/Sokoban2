package model.data.IO;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import model.data.level.Level;

public class MyXmlLevelSaver implements LevelSaver {
	/**
	 * @return level type as string
	 */
	@Override
	public String getFileTypeString() {
		return "xml";
	}
	/**
	 * the method saves the level object into an .XML file
	 * @param l the level that it needs to save
	 * @param file gets an output stream includes the file path
	 * 
	 */
	public void saveLevel(Level l, OutputStream file) throws FileNotFoundException, IOException {

		XMLEncoder xos = new XMLEncoder(new BufferedOutputStream(file));
		xos.writeObject(l);
		xos.close();

	}

}
