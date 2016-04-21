package nl.project.user;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import nl.project.mvc.EntityManagerManager;

public class UserDao {
	/**
	 * Maak een nieuw user aan en sla die op in de database
	 */
	public static User create(String name, String surname) {
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setTeams(new ArrayList<>());
		create(user);
		return user;
	}

	public static User create(User user) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		if (user != null) {
			em.persist(user);
		}

		t.commit();
		em.close();
		
		return user;
	}
	
	public static User find(User user) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Query q = em.createQuery("SELECT u FROM User u WHERE u.name=:name AND u.surname=:surname");
		user = (User) q.setParameter("name", user.getName()).setParameter("surname", user.getSurname())
				.getSingleResult();
		
		t.commit();
		em.close();
		System.out.println(user);
		return user;
	}

	public static User find(String name, String surname) {
		User user = new User(name, surname);
		return find(user);
	}



	/**
	 * Verwijder een user uit de database
	 */
	public static void remove(Long id) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		User user = em.find(User.class, id);
		if (user != null) {
			em.remove(user);
		}
		t.commit();
		em.close();
	}

	/**
	 * Haal een user op a.d.h.v. zijn id
	 */
	public static User findById(Long id) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		User user = em.find(User.class, id);
		t.commit();
		em.close();
		return user;
	}

	/**
	 * Haal een user op a.d.h.v. zijn naam
	 */
	public static User findByName(String name) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		User user = em.find(User.class,
				em.createQuery("SELECT id FROM TABLE user where user.name = :" + name).getSingleResult());
		t.commit();
		em.close();
		return user;
	}

	/**
	 * Haal alle users op uit de database
	 */
	public static List<User> all() {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<User> users = em.createQuery("from User", User.class).getResultList();
		t.commit();
		em.close();
		return users;
	}

	/**
	 * Update een user adhv id
	 */
	public static User update(Long id, User userUpdate) {
		EntityManager em = EntityManagerManager.getEntityManager();

		EntityTransaction t = em.getTransaction();
		
			t.begin();
			User user = em.find(User.class, id);
			if (user == null || userUpdate == null) {
				System.err.println("One of users is null...");
				return null;
			}
			//(new org.apache.commons.beanutils.BeanUtilsBean()).copyProperties(user, userUpdate);
			if (userUpdate.getName() == null) {
				System.err.println("New user name is null...");
			} else {
				user.setName(userUpdate.getName());
				user.setSurname(userUpdate.getSurname());
			}
			em.persist(user);
			t.commit();
		 
			em.close();
			
			return user;
		
	}
	
	/**
	 * Returns true if a user exists. 
	 */
	public static boolean exist(User user) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Query q = em.createQuery("SELECT count(u) FROM User u WHERE u.name=:name AND u.surname=:surname AND NOT u.id=:id");
		q.setParameter("name", user.getName());
		q.setParameter("surname", user.getSurname());
		q.setParameter("id", user.getId() == null ? 0 : user.getId() );
		
		Long count = (Long) q.getSingleResult();
		
		
		t.commit();
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
