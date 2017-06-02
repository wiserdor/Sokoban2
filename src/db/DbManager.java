package db;

import java.util.Timer;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class DbManager {
	private static SessionFactory factory;

	private static int addLevelUsers(String userName, String levelName, Timer time, int steps) {
		Level lev = new Level(levelName);
		User usr = new User(userName);
		LevelUsers levuse = new LevelUsers(lev, usr, time, steps);
		Transaction tx = null;
		int levelUserId = 0;

		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			levelUserId = (Integer)session.save(levuse);
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	
        return levelUserId;
	}



	/*private static void printEmployees() {
			Session session = factory.openSession();
			try {
			Query<Employee> query = session.createQuery("from
			Employees");
			List<Employee> list = query.list();
			for (Employee e: list) {
			System.out.println(e);
			}
			}
			catch (HibernateException e) {
			e.printStackTrace();
			}
			finally {
			session.close();
			}
			}//
	// Method to print all employees whose names start with specified
	prefix*/

	private static void printAllUsersByTime(String l) {
			Session session = factory.openSession();
			Query query = session.createQuery("from LevelUsers lu where lu.levelName=:level order by time,steps");
			query.setParameter("level", l);
			List<LevelUsers> list = query.list();
			for (LevelUsers lu: list) {
			System.out.println(lu.getUserName());
			System.out.println(lu.getTime());
			System.out.println(lu.getSteps());

			}
			session.close();
			}
	
	private static void printAllUsersBySteps(String l) {
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.levelName=:level order by steps,time");
		query.setParameter("level", l);
		List<LevelUsers> list = query.list();
		for (LevelUsers lu: list) {
		System.out.println(lu.getUserName());
		System.out.println(lu.getSteps());
		System.out.println(lu.getTime());


		}
		session.close();
		}
	
	private static void printAllLevelsBySteps(String u) {
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.userName=:user order by steps,time,level");
		query.setParameter("user", u);
		List<LevelUsers> list = query.list();
		for (LevelUsers lu: list) {
		System.out.println(lu.getLevelname());
		System.out.println(lu.getSteps());
		System.out.println(lu.getTime());


		}
		session.close();
		}
	
	private static void printAllLevelsByTime(String u) {
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.userName=:user order by time,steps,level");
		query.setParameter("user", u);
		List<LevelUsers> list = query.list();
		for (LevelUsers lu: list) {
			System.out.println(lu.getLevelname());
		System.out.println(lu.getSteps());
		System.out.println(lu.getTime());


		}
		session.close();
		}


	private static void printAllLevelsByLevel(String u) {
		Session session = factory.openSession();
		Query query = session.createQuery("from LevelUsers lu where lu.userName=:user order by level,time,steps");
		query.setParameter("user", u);
		List<LevelUsers> list = query.list();
		for (LevelUsers lu: list) {
			System.out.println(lu.getLevelname());
		System.out.println(lu.getSteps());
		System.out.println(lu.getTime());


		}
		session.close();
		}


}
