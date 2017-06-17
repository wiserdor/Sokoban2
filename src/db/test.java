package db;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class test {
	private static SessionFactory factory;

	public static void main(String[] args)
	{
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		
		 DbManager db= new DbManager(factory);
	     //DbManager.addLevelUsers("levelName", "carmi", 0.2, 4);
		//DbManager.printAllUsersBySteps("carmi");
	}

}
