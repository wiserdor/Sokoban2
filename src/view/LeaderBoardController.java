package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import db.DbManager;
import db.LevelUsers;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LeaderBoardController implements Initializable {
	private ObservableList<LevelUsers> data;

	ObservableList<String> options = FXCollections.observableArrayList("UserName", "LevelName");
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	DbManager db = new DbManager(factory);
	String levelName = "";
	int steps = -1;
	double time = -1;
	@FXML
	private Button SubmitButton;
	@FXML
	private Button bt1;
	@FXML
	private TableView<LevelUsers> levelUsersTable;
	@FXML
	private TextField userNameInput;
	MainWindowController mw;
	@FXML
	private TextField nameFilter;
	@FXML
	private TextField levelFilter;
	@FXML
	private ComboBox<String> orderByDrop;

	public void initVariable(String levelName, int steps, double time) {
		this.levelName = levelName;
		this.steps = steps;
		this.time = time;

	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void close() {
		Stage stage = (Stage) bt1.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				orderByDrop.setItems(options);
				orderByDrop.valueProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue ov, String t, String t1) {
						Session session = factory.openSession();
						data.clear();
						data.addAll(db.orderBy(t1));
						session.close();
					}
				});
				data = FXCollections.observableArrayList();

				levelUsersTable.setEditable(true);

				// LevelUsers table columns Initialization
				TableColumn<LevelUsers, String> column1 = new TableColumn<>("Level Name");
				column1.setCellValueFactory(new PropertyValueFactory<>("LevelName"));
				column1.setMinWidth(100);
				column1.setMaxWidth(100);

				TableColumn<LevelUsers, String> column2 = new TableColumn<>("UserName");
				column2.setCellValueFactory(new PropertyValueFactory<>("UserName"));
				column2.setMinWidth(150);
				column2.setMaxWidth(250);

				TableColumn<LevelUsers, String> column3 = new TableColumn<>("Steps");
				column3.setCellValueFactory(new PropertyValueFactory<>("Steps"));
				column3.setMinWidth(75);
				column3.setMaxWidth(75);

				TableColumn<LevelUsers, String> column4 = new TableColumn<>("Finish Time");
				column4.setCellValueFactory(new PropertyValueFactory<>("Time"));
				column4.setMinWidth(90);
				column4.setMaxWidth(90);

				nameFilter.textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						updateUserFilter(newValue);
					}
				});
				SubmitButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if ((userNameInput.getText() != null && !userNameInput.getText().isEmpty())) {
							if (addUserLevelScore(levelName) == false) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										Alert alert = new Alert(AlertType.ERROR, "User Name Already exists",
												ButtonType.OK);
										alert.showAndWait();
									}
								});
							} else
								close();

						}
					}
				});
				levelFilter.textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						updateLevelFilter(newValue);
					}
				});

				levelUsersTable.getColumns().addAll(column1, column2, column3, column4);

				levelUsersTable.setItems(data);
				data.clear();
				Session session = factory.openSession();

				Query<LevelUsers> query = session.createQuery("from LevelUsers");
				List<LevelUsers> list = query.list();

				data.addAll(list);

				session.close();
				// Mouse click event handler
				levelUsersTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						LevelUsers LevelUsers = levelUsersTable.getSelectionModel().getSelectedItem();
						if (LevelUsers != null)
							displayLevelUsers(LevelUsers);
					}
				});
			}
		});
	}

	boolean addUserLevelScore(String UserName) {

		Session session = factory.openSession();
		@SuppressWarnings("unchecked")
		Query<LevelUsers> query = session
				.createQuery("from LevelUsers lu where lu.UserName LIKE :value1 and lu.LevelName LIKE :value2");
		query.setParameter("value1", UserName + "%");
		query.setParameter("value2", this.levelName + "%");
		List<LevelUsers> list = query.list();

		if (list.size() == 0) {
			DbManager.addLevelUsers(userNameInput.getText(), levelName, time, steps);
			session.close();
			return true;
		}
		session.close();
		return false;
	}
	public void displayLevelUsers(LevelUsers lu) {
		List<LevelUsers> list = db.getUserScores(lu.getUserName());
		data.clear();
		data.addAll(list);
	}
	

	public void refreshList() {
		updateUserFilter("");
	}

	public void updateLevelFilter(String search) {
		Session session = factory.openSession();

		@SuppressWarnings("unchecked")
		Query<LevelUsers> query = session.createQuery("from LevelUsers");
		List<LevelUsers> list = query.list();

		int len = list.size();
		int i = 0;

		while (i < len) {
			if (list.get(i).getLevelName().toLowerCase().startsWith(search.toLowerCase()) == false) {
				list.remove(i);
				len--;
			} else
				i++;
		}
		// New data set up
		data.clear();
		data.addAll(list);
		session.close();
	}

	public void updateUserFilter(String search) {
		Session session = factory.openSession();

		@SuppressWarnings("unchecked")
		Query<LevelUsers> query = session.createQuery("from LevelUsers");
		List<LevelUsers> list = query.list();

		int len = list.size();
		int i = 0;

		while (i < len) {
			if (list.get(i).getUserName().toLowerCase().startsWith(search.toLowerCase()) == false) {
				list.remove(i);
				len--;
			} else
				i++;
		}
		// New data set up
		data.clear();
		data.addAll(list);
		session.close();
	}

}
