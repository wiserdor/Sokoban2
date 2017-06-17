package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Users")
public class User  {
	@Id
	@Column(name="UserName")
	private String UserName;
	
	
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public User() {
	
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
