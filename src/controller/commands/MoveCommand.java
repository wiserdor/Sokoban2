package controller.commands;

import model.Model;
import model.policy.MySokobanPolicy;

public class MoveCommand extends Commands {
	private Model model;

	public MoveCommand(Model model) {
		this.model = model;
	}

	public void execute() {
		model.move(params);

	}

}
