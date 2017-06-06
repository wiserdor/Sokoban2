package db;

import java.sql.Time;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class DbManager 
{
	private static SessionFactory factory;
	
	public DbManager(SessionFactory factory)
	{
		this.factory=factory;
	}
	public static void addOnlyUser(String userName)
	{
		User usr = new User(userName);
		Transaction tx = null;
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			tx.commit();
			
		} catch (HibernateException e) 
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally 
		{
			session.close();
		}
	}
	public static void addLevelUsers(String userName, String levelName, Time time, int steps) {
		Level lev = new Level(levelName);
		User usr = new User(userName);
		LevelUsers levuse = new LevelUsers(levelName, userName, time, steps);
		Transaction tx = null;
		int levelUserId=0;
		Session session = factory.openSession();
		

		try {
			tx = session.beginTransaction();
			session.save(lev);
			session.save(usr);
			session.save(levuse);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	
	}


	public static void printAllUsersByTime(String l) {
		
//		User user = getSomeUser();
//		Query query = session.createQuery("from UserOwnsAvatar where user.username =  :username");
//		query.setParameter("username", user);
		
			Session session = factory.openSession();
			Query query = session.createQuery("FROM LevelUsers lu WHERE lu.levelName LIKE = :value order by time,steps");
			query.setParameter("value", l);
			List<LevelUsers> list = query.list();
			for (LevelUsers lu: list) {
			System.out.println(lu.getUserName());
			System.out.println(lu.getTime());
			System.out.println(lu.getSteps());

			}
			session.close();
			}
	
	public static void printAllUsersBySteps(String prefix) {
		System.out.println(prefix);
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.LevelName LIKE:prefix"); // lu where lu.LevelName LIKE :level order by lu.Steps,lu.Time"
		query.setParameter("prefix", prefix+"%");

				
		List<LevelUsers> list = query.list();
		for (LevelUsers user: list) {	
		System.out.println(user.getUserName());
		System.out.println(user.getSteps());
		System.out.println(user.getTime());


		}
		session.close();
		}
	
	public static void printAllLevelsBySteps(String u) {
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.userName=:User order by steps,time,level");
		query.setParameter("User", u);
		List<LevelUsers> list = query.list();
		for (LevelUsers lu: list) {
		System.out.println(lu.getLevelName());
		System.out.println(lu.getSteps());
		System.out.println(lu.getTime());


		}
		session.close();
		}
	
	public static void printAllLevelsByTime(String u) {
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.userName=User order by time,steps,level");
		query.setParameter("User", u);
		List<LevelUsers> list = query.list();
		for (LevelUsers lu: list) {
			System.out.println(lu.getLevelName());
		System.out.println(lu.getSteps());
		System.out.println(lu.getTime());


		}
		session.close();
		}


	public static void printAllLevelsByLevel(String u) {
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.userName=:User order by level,time,steps");
		query.setParameter("User", u);
		List<LevelUsers> list = query.list();
		for (LevelUsers lu: list) {
			System.out.println(lu.getLevelName());
		System.out.println(lu.getSteps());
		System.out.println(lu.getTime());


		}
		session.close();
		}


}
