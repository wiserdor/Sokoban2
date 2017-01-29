package controller.commands;

import model.Model;

public class MoveCommand extends Commands {
	private Model model;

	public MoveCommand(Model model) {
		this.model = model;
	}

	public void execute() {
		if (params != null)
			model.move(params);

	}

}
