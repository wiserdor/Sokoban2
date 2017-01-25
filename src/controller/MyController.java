package controller;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import commands.Command;
import controller.commands.Commands;
import controller.commands.DisplayCommand;
import controller.commands.LoadCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveCommand;
import model.Model;
import view.View;

public class MyController implements Controller {
	private Model model;
	private View view;
	private BlockingQueue<Commands> queue;
	private boolean isStopped = false;
	private LoadCommand LevelHolder;
	
	public MyController(Model model, View view) {
		this.model = model;
		this.view = view;
		start();
	}


	public void insertCommand(String[] cmdSplit) {
		Commands cmd = null;
		switch (cmdSplit[0]) {
		case "load":
		case "Load":
			LevelHolder = new LoadCommand(cmdSplit[1]);
			cmd = LevelHolder;
			break;
		case "display":
		case "Display":
			if (LevelHolder != null)
				cmd = new DisplayCommand(LevelHolder);
			break;
		case "Move":
		case "move":
			if (LevelHolder != null)
				cmd = new MoveCommand(LevelHolder, cmdSplit[1]);
			break;
		case "save":
		case "Save":
			if (LevelHolder != null)
				cmd = new SaveCommand(LevelHolder, cmdSplit[1]);
			break;
		default:
			cmd = null;
			break;
		}
		try {
			queue.put(cmd);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!isStopped) {
					try {
						Commands cmd = queue.poll(1, TimeUnit.SECONDS);
						if (cmd != null)
							cmd.execute();
					} catch (InterruptedException | ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	@Override
	public void stop() {
		isStopped = true;
	}
	public void update(Observable o, Object arg) {
		String[] params = (String[]) arg;
		insertCommand(params);

	}

}
