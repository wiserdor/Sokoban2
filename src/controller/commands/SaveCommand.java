package controller.commands;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Model;
import model.data.IO.LevelSaveCreators;

public class SaveCommand extends Commands {
	private Model model;

	public SaveCommand(Model model) {
		this.model=model;
	}

	/**
	 * will create saver and send the level to the saver.
	 * 
	 * @see LevelSaveCreators
	 * 
	 */
	@Override
	public void execute() throws FileNotFoundException, IOException {
		model.save(params);
		
	}

}
