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
	@Column(name="levelUserId")
	private int levelUserId;


	
	@JoinColumn(name="LevelName")
	private String LevelName;
	
	
	@JoinColumn(name="UserName")
	private String UserName;
	
	@Column(name="Time")
	private Double Time;
	
	@Column(name="Steps")
	private int Steps;
	
	public String getLevelName() {
		return LevelName;
	}

	public void setLevelName(String LevelName) {
		this.LevelName = LevelName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	
	
	

	public LevelUsers( String LevelName, String userName, Double Time, int steps) {
		this.LevelName = LevelName;
		UserName = userName;
		this.Time = Time;
		Steps = steps;
	}

	public Double getTime() {
		return Time;
	}

	public void setTime(Double Time) {
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
