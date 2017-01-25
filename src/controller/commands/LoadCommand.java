package controller.commands;

/**
 * Class operates the LevelLoader creator and contains the level
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import model.data.IO.LevelLoadCreators;
import model.data.level.Level;

public class LoadCommand implements Commands {
	private String filePath;
	private Level level;

	/**
	 * Constructor must get file path
	 * 
	 * @param filePath
	 *            the file path
	 */
	public LoadCommand(String filePath) {
		super();
		this.filePath = filePath;
	}

	/**
	 * will create Loader and send the level to it.
	 * 
	 * @see LevelLoadCreators
	 * 
	 */
	public void execute() throws ClassNotFoundException, IOException {
		InputStream in = new FileInputStream(filePath);
		LevelLoadCreators lc = new LevelLoadCreators();
		this.level = lc.CreateLoader(filePath).loadLevel(in);

		// if(level!=null)

	}

	public final String getFilePath() {
		return filePath;
	}

	public final void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public final Level getLevel() {
		return level;
	}

	public final void setLevel(Level level) {
		this.level = level;
	}

}
