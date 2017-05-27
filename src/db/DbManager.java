package db;

import java.util.Timer;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DbManager {

	private static void addScore(String userName, String levelName, Timer time, int steps) {
		lev = new Level(levelName, userName);
		usr = new User(userName,levelName);
		levuse = new LevelUser(userName, levelName, time, steps);
		Transaction tx = null;
		int scoreID = 0;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			scoreID = (Integer) session.save(emp);
			scoreID = (Integer) session.save(emp);
			scoreID = (Integer) session.save(emp);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return empID;
	}
	private static int addUser(String userName, String levelName, Timer time, int steps) {
		lev = new Level(levelName, userName);
		usr = new User(userName,levelName);
		levuse = new LevelUser(userName, levelName, time, steps);
		Transaction tx = null;
		int scoreID = 0;
		
		
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			scoreID = (Integer) session.save(emp);
			scoreID = (Integer) session.save(emp);
			scoreID = (Integer) session.save(emp);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return empID;
	}

	private static int addScore(String userName, String levelName, Timer time, int steps) {

	}

	// Method to print all employees
	private static void printEmployees() {
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
			}
	// Method to print all employees whose names start with specified
	prefix

	private static void printEmployeesWhoseNameStartsWith(String
			prefix) {
			Session session = factory.openSession();
			Query query = session.createQuery("from Employees E where
			E.first_name LIKE :prefix");
			query.setParameter("prefix", prefix + "%");
			List<Employee> list = query.list();
			for (Employee e: list) {
			System.out.println(e);
			}
			session.close();
			}

	// Method to update a salary for an employee
	private static void updateSalary(int empId, double salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, empId);
			emp.setSalary(salary);
			session.update(emp);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// Method to delete an employee
	private static void deleteEmployee(int empId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, empId);
			session.delete(emp);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
