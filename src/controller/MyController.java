package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.Commands;
import controller.commands.DisplayCommand;
import controller.commands.ExitCommand;
import controller.commands.LoadCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveCommand;
import controller.server.CLI;
import controller.server.SokobanServer;
import model.Model;
import view.View;

public class MyController implements Controller {
	private Model model;
	private View view;
	private SokobanServer server;
	private BlockingQueue<Commands> queue;
	private boolean isStopped = false;

	public MyController(Model model, View view,int port) {
		this.model = model;
		this.view = view;
		this.server=new SokobanServer(port, new CLI(null, null, null));
		queue = new ArrayBlockingQueue<Commands>(10);

		start();
	}

	public void insertCommand(String[] cmdSplit) {
		Commands cmd = null;
		switch (cmdSplit[0]) {
		case "load":
		case "Load":
			cmd = new LoadCommand(model);
			cmd.setParams(cmdSplit[1]);
			break;
		case "display":
		case "Display":
			cmd = new DisplayCommand(model, view);
			break;
		case "Move":
		case "move":
			cmd = new MoveCommand(model);
			cmd.setParams(cmdSplit[1]);
			break;
		case "save":
		case "Save":
			cmd = new SaveCommand(model);
			cmd.setParams(cmdSplit[1]);
			break;
		case "exit":
		case "Exit":
			cmd = new ExitCommand(this,view);

			break;
		default:
			cmd = null;
			break;
		}
		if (cmd != null)
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
		if (o.equals(view)) {
			String[] params = (String[]) arg;
			insertCommand(params);
		} else if (o.equals(model)) {
			if(model.isFinished()){
				try {
					view.setWin();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if ((boolean) arg) {
				try {
					view.display(model.getDisplay());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
