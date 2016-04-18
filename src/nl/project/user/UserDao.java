package nl.project.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nl.project.mvc.EntityManagerManager;
import nl.project.team.Team;
import nl.project.team.TeamDao;

public class UserDao {	
	/**
	 * Maak een nieuw user aan en sla die op in de database
	 */	
	public static User create(String name, String surname){
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setTeams(new ArrayList<>());
		
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist( user );
		t.commit();
		em.close();
		
		return user;
	}
	
	/**
	 * Verwijder een user uit de database
	 */
	public static void remove(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		User user = em.find(User.class, id);
		if(user != null){
			em.remove( user );
		}
		t.commit();
		em.close();
	}
	
	/**
	 * Haal een user op a.d.h.v. zijn id
	 */
	public static User find(Long id){
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
	public static User find(String name){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		User user = em.find(User.class, em.createQuery("SELECT id FROM TABLE user where user.name = :"+name).getSingleResult());
		t.commit();
		em.close();
		return user;
	}
	
	/**
	 * Haal alle users op uit de database
	 */	
	public static List<User> all(){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<User> users = em.createQuery("from User", User.class).getResultList();
		t.commit();
		em.close();
		return users;
	}
}
