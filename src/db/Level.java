package db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import model.data.level.Level;

@Entity(name="Levels")
public class Level {
	@Id
	@Column(name="LevelName")
	private String levelname;
	
	public Level(String levelname, List<User> users) {
		super();
		this.levelname = levelname;
		this.users = users;
	}

	@OneToMany
	@JoinColumn(name="LevelName")
	private List<User> users = new ArrayList<User>();

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}



}
