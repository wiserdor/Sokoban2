package controller.commands;

import model.Model;
import view.View;

/**
 * Class operating the Exit session
 *
 */
public class ExitCommand extends Commands {
	private Model model;
	private View view;
	
	public ExitCommand(Model model, View view) {
		super();
		this.model = model;
		this.view = view;
	}

	public void execute() {
		System.out.println("exiting");
	
		
	}

}
