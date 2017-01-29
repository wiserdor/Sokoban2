package Boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.MyController;
import model.MyModel;
import view.CLI;
import view.Main;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		CLI viewCLI = new CLI(reader, writer, "Exit");
		
		MyController controller = new MyController(model, viewCLI);
		
		model.addObserver(controller);
		viewCLI.addObserver(controller);
		
		viewCLI.start();


	}

}
