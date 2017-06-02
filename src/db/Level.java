package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Levels")
public class Level {
	@Id
	@Column(name="LevelName") 
	private String levelname;
	
	


	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public Level (String name)
	{
		setLevelname(name);
	}
		
	@Override
	public String toString(){
		return "Level name [levelname]";
	}
}
