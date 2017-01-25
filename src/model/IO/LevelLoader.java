package IO;
/**
 *1. the separation between the data creator and the data holder, is achieved by creating different classes 
 *with different types of responsibilities:
 *		 *LevelLoader - responsible on defining a general load
 * 		  appearance , and by being an interface the load code is not implemented by this class.
 * 		 *The Classes that implements the LevelLoader interface, contains the load method according 
 * 		  to file type.
 * 
 *2.if we will decide to expand the file types ,which can be loaded, We will be able to do so 
 *by simply adding another class that implements the LevelLoader without changing the existing 
 *code. and thats how we are keeping the Open/Close 
 *
 *3.in order to Load a file, we are using a  class that contains the 
 *code that is responsible to load this specific file type.
 *we are not using the same method to load all file types.
 *
 *4.inputStream allows us to receive any kind of data .
 *oppose to string filename that limits us to file loading only 
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import Levels.Level;

public interface LevelLoader {
	public Level loadLevel(InputStream file) throws FileNotFoundException, IOException, ClassNotFoundException;

	public String getFileTypeString();
}
