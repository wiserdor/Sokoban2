package db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import model.data.level.Level;



@Entity(name="Users")
public class User {
	@Id
	@Column(name="UserName")
	private String userName;
	
	@OneToMany
	@JoinColumn(name="UserName")
	private List<Level> levels = new ArrayList<Level>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
}
