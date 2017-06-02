package db;

import java.util.Timer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name="LevelUsers")
public class LevelUsers  {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int levelUserId;
	
	@Column(name="LevelName")
	private Level levelname;
	
	@Column(name="UserName")
	private User UserName;
	
	public Level getLevelname() {
		return levelname;
	}

	public void setLevelname(Level levelname) {
		this.levelname = levelname;
	}

	public User getUserName() {
		return UserName;
	}

	public void setUserName(User userName) {
		UserName = userName;
	}

	@Column(name="Time")
	private Timer time;
	@Column(name="Steps")
	private int Steps;
	
	

	public LevelUsers( Level levelname, User userName, Timer time, int steps) {
		super();
		this.levelname = levelname;
		UserName = userName;
		this.time = time;
		Steps = steps;
	}

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


	
}
