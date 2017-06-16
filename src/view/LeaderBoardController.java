package view;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import db.DbManager;
import db.LevelUsers;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LeaderBoardController implements Initializable {
	private ObservableList<LevelUsers> data;
	ObservableList<String> options = FXCollections.observableArrayList("Name", "Level");
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	DbManager db=new DbManager(factory);

	@FXML
	private Button bt1;
	@FXML
	private TableView<LevelUsers> levelUsersTable;
	@FXML
	private TextField nameFilter;
	@FXML
	private TextField levelFilter;
	@FXML
	private ComboBox<String> orderByDrop;

	public void close() {
		Stage stage = (Stage) bt1.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				orderByDrop.setItems(options);
				orderByDrop.valueProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue ov, String t, String t1) {
						OrderBy(t1);
					}
				});
				data = FXCollections.observableArrayList();
				
				levelUsersTable.setEditable(true);

				// LevelUsers table columns Initialization
				TableColumn<LevelUsers, String> column1 = new TableColumn<>("Level");
				column1.setCellValueFactory(new PropertyValueFactory<>("levelID"));
				column1.setMinWidth(100);
				column1.setMaxWidth(100);

				TableColumn<LevelUsers, String> column2 = new TableColumn<>("UserName");
				column2.setCellValueFactory(new PropertyValueFactory<>("user"));
				column2.setMinWidth(150);
				column2.setMaxWidth(250);

				TableColumn<LevelUsers, String> column3 = new TableColumn<>("Steps");
				column3.setCellValueFactory(new PropertyValueFactory<>("steps"));
				column3.setMinWidth(75);
				column3.setMaxWidth(75);

				TableColumn<LevelUsers, String> column4 = new TableColumn<>("Finish Time");
				column4.setCellValueFactory(new PropertyValueFactory<>("time"));
				column4.setMinWidth(90);
				column4.setMaxWidth(90);

				nameFilter.textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						updateNameFilter(newValue);
					}
				});
				
				 levelFilter.textProperty().addListener(new
				 ChangeListener<String>() {
				 @Override
				 public void changed(ObservableValue<? extends String>
				 observable, String oldValue, String newValue) {
				 updateLevelFiltere(newValue);
				 }
				 });

				// Columns set up
				levelUsersTable.getColumns().addAll(column1, column2, column3, column4);

				// Data set up
				levelUsersTable.setItems(data);

				// Mouse click event handler
				// levelUsersTable.setOnMouseClicked(new
				// EventHandler<MouseEvent>() {
				// @Override
				// public void handle(MouseEvent arg0) {
				// LevelUsers LevelUsers =
				// levelUsersTable.getSelectionModel().getSelectedItem();
				// if (LevelUsers != null)
				// displayLevelUsers(LevelUsers);
				// }
				// });
			}
		});
	}

	protected void displayLevelUsers(LevelUsers levelUsers) {

	}

	protected void updateLevelFiltere(String newValue) {
		// TODO Auto-generated method stub

	}

	protected void updateNameFilter(String newValue) {
		// TODO Auto-generated method stub

	}

	protected void OrderBy(String t1) {
		if(t1=="Name"){
			
		} else if(t1=="Level"){
			
		}

	}
}
