package Boot;

import controller.MyController;
import model.MyModel;
import view.CLI;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		CLI view = new CLI();
		MyController controller = new MyController(model, view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		
		view.start();

	}

}
