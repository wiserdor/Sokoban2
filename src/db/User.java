package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Users")
public class User  {
	@Id
	@Column(name="UserName")
	private String userName;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User (String name) 
	{
		setUserName(name);
	}
	
	@Override
	public String toString ()
	{
		return "User [userName]";
	}
}
