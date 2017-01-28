package controller.commands;

/**
 * Class operates the display of board
 */
import java.io.IOException;

import model.Model;
import model.data.CellTypes.CellType;
import view.View;

public class DisplayCommand extends Commands {
	private Model model;
	private View view;

	public DisplayCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void execute() throws IOException {
		Character[][] Board = model.getDisplay();
		if (Board != null) {
			view.display(Board);
		} else
			System.out.println("Please Load Level");
	}

}
