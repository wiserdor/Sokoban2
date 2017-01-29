package view;
	


import controller.MyController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyModel;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MaimWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();	
			MainWindowController view = loader.getController();
											
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			init(view);
			primaryStage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init(MainWindowController view) {
		MyModel model = new MyModel();
		MyController controller = new MyController(model, view,1234);
		

		model.addObserver(controller);
		view.addObserver(controller);

		view.start();	

		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
