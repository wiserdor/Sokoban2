package db;

import java.util.Timer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="LevelUsers")
public class LevelUsers {
	@Id
	@Column(name="LevelName")
	private String levelname;
	
	@Column(name="UserName")
	private String UserName;
	@Column(name="Time")
	private Timer time;
	@Column(name="Steps")
	private int Steps;
	public Timer getTime() {
		return time;
	}

	public void setTime(Timer time) {
		this.time = time;
	}

	public int getSteps() {
		return Steps;
	}

	public void setSteps(int steps) {
		Steps = steps;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public LevelUsers(String levelname, String userName, Timer time, int steps) {
		super();
		this.levelname = levelname;
		UserName = userName;
		this.time = time;
		Steps = steps;
	}

	
}
