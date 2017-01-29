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

	@Override
	public void execute() throws FileNotFoundException, IOException {
		if (params != null)
		model.save(params);
		
	}

}
