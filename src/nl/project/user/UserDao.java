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
		find(user);

		create(user);
		return user;
	}

	public static User find(User user) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query q = em.createQuery("SELECT u FROM User u WHERE u.name='Myrte'");
		user = (User) q.getSingleResult();
		t.commit();
		em.close();
		System.out.println(user);
		return user;
	}
	
	public static User find(String name, String surname) {
		User user = new User(name, surname);
		return find(user);
	}
	
	
	public static void create(User user) {
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		/*
		List<User> users = em.createQuery("from User", User.class).getResultList();
		System.out.println(users);
		if (users.contains(user) ) {
			System.out.println("There is already a user with this name and surname.");
		} else {
			em.persist(user);
		}
		*/
		find(user);
		if (user != null) {
			em.persist(user);
		}		
		
		t.commit();
		em.close();
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
	public static void update(Long id, User userUpdate) {
		EntityManager em = EntityManagerManager.getEntityManager();

		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			User user = em.find(User.class, id);
			if (user == null) {
				// user does not exist
				// error message to 404 page
				return;
			}
			(new org.apache.commons.beanutils.BeanUtilsBean()).copyProperties(user, userUpdate);
			em.persist(user);
			t.commit();
		} catch (InvocationTargetException e) {
			System.out.println("hmm error");
		} catch (IllegalAccessException e) {
			System.out.println("hmm error");
		} finally {
			em.close();
		}

	}
}
