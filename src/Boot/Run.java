package Boot;

import controller.MyGameController;
import model.MyModel;
import view.CLI;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		CLI view = new CLI();
		MyGameController controller = new MyGameController(model, view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		
		view.start();

	}

}
