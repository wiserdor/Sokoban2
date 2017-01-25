package controller.commands;
/**
 * Class connects the current level to the matching saver
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import model.data.IO.LevelSaveCreators;

public class SaveCommand implements Commands {
	private LoadCommand levelHolder;
	private String filePath;
/**
 * Constructor that must get levelholder and filepath 
 * @param levelHolder 
 * @param filePath the path of the file we wants to load from
 * @see LoadCommand
 */
	public SaveCommand(LoadCommand levelHolder, String filePath) {
		this.levelHolder = levelHolder;
		this.filePath = filePath;
	}

	/**
	 * will create saver and send the level to the saver.
	 * 
	 * @see LevelSaveCreators
	 * 
	 */
	@Override
	public void execute() throws FileNotFoundException, IOException {
		OutputStream out = new FileOutputStream(filePath);
		LevelSaveCreators sc = new LevelSaveCreators();
		sc.Createsaver(filePath).saveLevel(levelHolder.getLevel(), out);;
		
	}

}
