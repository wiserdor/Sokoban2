package controller.commands;

/**
 * Class operates the display of board
 */
import java.io.IOException;
import controller.server.ClientHandler;
import model.Model;
import view.View;

public class DisplayCommand extends Commands {
	private Model model;
	private View view;
	private ClientHandler cli;

	public DisplayCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	public DisplayCommand(Model model, View view,ClientHandler cli) {
		this.model = model;
		this.view = view;
		this.cli=cli;
	}

	@Override
	public void execute() throws IOException {
		Character[][] Board = model.getDisplay();
		if (Board != null) {
			view.display(Board,model.countSteps());
			if(cli!=null)
				cli.display(Board);
		} else{
			System.out.println("Please Load Level");
			if(cli!=null){
				cli.addMessageToQueue("Please Load Level");
			}
		}
			
		
	}

}
