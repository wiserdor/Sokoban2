package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LeaderBoardController {
	@FXML
	private Button bt1;
	public void close(){
		Stage stage = (Stage) bt1.getScene().getWindow();
	    stage.close();
	}	
}
