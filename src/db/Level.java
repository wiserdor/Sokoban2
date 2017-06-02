package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Levels")
public class Level {
	@Id
	@Column(name="LevelName") 
	private String LevelName;
	
	


	public Level() {
	
	}

	public String getLevelName() {
		return LevelName;
	}

	public void setLevelName(String LevelName) {
		this.LevelName = LevelName;
	}

	public Level (String name)
	{
		setLevelName(name);
	}
		
	@Override
	public String toString(){
		return "Level name [LevelName]";
	}
}
