package controller.commands;

import java.io.IOException;

import model.Model;

public class LoadCommand extends Commands {
	private Model model;
	
	/**
	 * Constructor must get file path
	 * 
	 * @param filePath
	 *            the file path
	 */
	public LoadCommand(Model model) {
		super();
		this.model=model;
	}

	public void execute() throws ClassNotFoundException, IOException {
		
		if(this.params!=null){
			model.load(this.params);
		}

	}

}
