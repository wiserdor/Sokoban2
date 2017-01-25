package model.data.IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import model.data.level.Level;

public interface LevelSaver {

	public String getFileTypeString();

	public void saveLevel(Level l, OutputStream file) throws FileNotFoundException, IOException;

}
