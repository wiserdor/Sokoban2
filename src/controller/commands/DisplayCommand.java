package controller.commands;

/**
 * Class operates the display of board
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import model.data.IO.MyTextLevelSaver;

public class DisplayCommand implements Commands {
	private LoadCommand levelHolder;

	/**
	 * Constructor must get levelHolder
	 * @param levelHolder 
	 */
	public DisplayCommand(LoadCommand levelHolder) {
		this.levelHolder = levelHolder;
	}
	/**
	 * Class will send the console output stream into a class that can interpret 
	 * the Level Board 
	 * @see MyTextLevelSaver
	 */
	@Override
	public void execute() throws IOException {
		MyTextLevelSaver s = new MyTextLevelSaver();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		s.saveBoard(levelHolder.getLevel(), writer);
		writer.flush();
		;
	}

}
