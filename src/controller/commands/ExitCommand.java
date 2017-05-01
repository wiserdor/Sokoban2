package controller.commands;

import controller.Controller;
import controller.server.ClientHandler;
import view.View;

/**
 * Class operating the Exit session
 *
 */
public class ExitCommand extends Commands {
	private Controller controller;
	private View view;

	
	public ExitCommand(Controller controller, View view) {
		super();
		this.controller=controller;
		this.view = view;
	}


	public void execute() {
		System.out.println("exiting");
		controller.stop();
		view.exitGui();
		
		
		
	}

}
