package db;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="LevelUsers")
public class LevelUsers  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int levelUserId;
	public int getLevelUserId() {
		return levelUserId;
	}

	public void setLevelUserId(int levelUserId) {
		this.levelUserId = levelUserId;
	}

	@OneToOne
	@JoinColumn(name="LevelName")
	private Level LevelName;
	@OneToOne
	@JoinColumn(name="UserName")
	private User UserName;
	
	@Column(name="Time")
	private Time Time;
	
	@Column(name="Steps")
	private int Steps;
	public Level getLevelName() {
		return LevelName;
	}

	public void setLevelName(Level LevelName) {
		this.LevelName = LevelName;
	}

	public User getUserName() {
		return UserName;
	}

	public void setUserName(User userName) {
		UserName = userName;
	}

	
	
	

	public LevelUsers( Level LevelName, User userName, Time Time, int steps) {
		this.LevelName = LevelName;
		UserName = userName;
		this.Time = Time;
		Steps = steps;
	}

	public Time getTime() {
		return Time;
	}

	public void setTime(Time Time) {
		this.Time = Time;
	}

	public LevelUsers() {
		
	}

	public int getSteps() {
		return Steps;
	}

	public void setSteps(int steps) {
		Steps = steps;
	}


	
}
