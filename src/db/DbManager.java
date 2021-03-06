package db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DbManager {
	private static SessionFactory factory;

	public DbManager(SessionFactory factory) {
		this.factory = factory;
	}

	public static void addOnlyUser(String userName) {
		User usr = new User(userName);
		Transaction tx = null;
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void addLevelUsers(String userName, String levelName, Double time, int steps) {
		Level lev = new Level(levelName);
		User usr = new User(userName);
		LevelUsers levuse = new LevelUsers(levelName, userName, time, steps);
		Transaction tx = null;
		int levelUserId = 0;
		Session session = factory.openSession();

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from LevelUsers lu where lu.LevelName LIKE :value");
			query.setParameter("value", levelName + "%");
			List<LevelUsers> list = query.list();
			if (list.size() == 0) {
				session.save(lev);
			}
			Query query2 = session.createQuery("from LevelUsers lu where lu.UserName LIKE :value");
			query2.setParameter("value", userName + "%");
			List<LevelUsers> list2 = query2.list();
			if (list2.size() == 0) {
				session.save(usr);
			}
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

		Session session = factory.openSession();
		Query query = session
				.createQuery("FROM LevelUsers lu WHERE lu.levelName LIKE :value order by lu.time,lu.steps");
		query.setParameter("value", l + "%");
		List<LevelUsers> list = query.list();
		for (LevelUsers lu : list) {
			System.out.println(lu.getUserName());
			System.out.println(lu.getTime());
			System.out.println(lu.getSteps());

		}
		session.close();
	}
	public static List<LevelUsers> getUserScores(String l) {

		Session session = factory.openSession();
		Query query = session
				.createQuery("FROM LevelUsers lu WHERE lu.UserName LIKE :value ");
		query.setParameter("value", l + "%");
		List<LevelUsers> list = query.list();
		session.close();
		return list;
	}

	public static List<LevelUsers> orderBy(String l) {
		System.out.println(l);
		Session session = factory.openSession();
		Query query = session
				.createQuery("from LevelUsers lu order by lu.:value");
		query.setParameter("value", l + "%");
		List<LevelUsers> list = query.list();
		session.close();
		return list;
	}

	public static void printAllLevelsBySteps(String u) {
		Session session = factory.openSession();
		Query query = session
				.createQuery("from LevelUsers lu where lu.userName like :value order by lu.steps,lu.time,lu.LevelName");
		query.setParameter("value", u + "%");
		List<LevelUsers> list = query.list();
		for (LevelUsers lu : list) {
			System.out.println(lu.getLevelName());
			System.out.println(lu.getSteps());
			System.out.println(lu.getTime());

		}
		session.close();
	}

	public static void printAllLevelsByTime(String u) {
		Session session = factory.openSession();
		Query query = session
				.createQuery("from LevelUsers lu where lu.userName like :value order by lu.time,lu.steps,lu.LevelName");
		query.setParameter("value", u + "%");
		List<LevelUsers> list = query.list();
		for (LevelUsers lu : list) {
			System.out.println(lu.getLevelName());
			System.out.println(lu.getSteps());
			System.out.println(lu.getTime());

		}
		session.close();
	}

	public static void printAllLevelsByLevel(String u) {
		Session session = factory.openSession();
		Query query = session
				.createQuery("from LevelUsers lu where lu.userName like :value order by lu.LevelName,lu.time,lu.steps");
		query.setParameter("value", u + "%");
		List<LevelUsers> list = query.list();
		for (LevelUsers lu : list) {
			System.out.println(lu.getLevelName());
			System.out.println(lu.getSteps());
			System.out.println(lu.getTime());

		}
		session.close();
	}

}
