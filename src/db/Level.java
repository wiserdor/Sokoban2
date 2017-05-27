package db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import model.data.level.Level;

@Entity(name="Levels")
public class Level {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
