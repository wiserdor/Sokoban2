package controller.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.server.ClientHandler;
import model.Model;
import view.View;

public class MoveCommand extends Commands {
	private Model model;
	private View view;
	private ClientHandler cli;

	public MoveCommand(Model model, View view, ClientHandler cli) {
		this.model = model;
		this.view = view;
		this.cli = cli;
	}

	public void execute() {
		if (params != null) {
			String str;
			str = model.move(params);
			if (cli != null) {
				cli.addMessageToQueue(str);
			}
			try {
				view.display(model.getDisplay(), model.countSteps());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (model.isFinished()) {
				try {
					view.setWin();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
