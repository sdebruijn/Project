package nl.project.event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nl.project.mvc.EntityManagerManager;
import nl.project.team.Team;
import nl.project.user.User;
import nl.project.user.UserDao;

public abstract class EventDao {
	
	/**
	 * Maak een nieuwe wedstrijd aan en sla die op in de database
	 */	
	public static void createMatch(Event event){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist( event );
		t.commit();
		em.close();
	}
	
	/**
	 * Haal alle events op uit de database
	 */	
	public static List<Event> all(){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<Event> events = em.createQuery("from Event", Event.class).getResultList();
		t.commit();
		em.close();
		return events;
	}
	
	/**
	 * Haal een event op a.d.h.v. zijn id
	 */
	public static Event find(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Event event = em.find(Event.class, id);
		t.commit();
		em.close();
		return event;
	}
	
	/**
	 * Voegt een present user toe aan het event, als hij nog nergens bestaat
	 */
	public static void addPresent (Long event_id, Long user_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Event event = em.find(Event.class, event_id);
		boolean exists = false;
		User equ = null;
		
		List<User> pusers = event.getPresent();
		List<User> ausers = event.getAbsent();
		for (User u : pusers){
			if (u.getId().equals(user_id)){
				exists = true;	}}
		for (User u : ausers){
			if (u.getId().equals(user_id)){
				equ = UserDao.find(user_id);	}}
		
		if (!exists){
			event.addPresent(UserDao.find(user_id));
		}
		
		if (equ != null){
			event.removeAbsent(equ);
		}
				
		t.commit();
		em.close();
	}
	
	/**
	 * Voegt een absent user toe aan het event, als hij nog nergens bestaat
	 */
	public static void addAbsent (Long event_id, Long user_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Event event = em.find(Event.class, event_id);
		boolean exists = false;
		User equ = null;
		
		List<User> pusers = event.getPresent();
		List<User> ausers = event.getAbsent();
		for (User u : ausers){
			if (u.getId().equals(user_id)){
				exists = true;	}}
		for (User u : pusers){
			if (u.getId().equals(user_id)){
				equ = UserDao.find(user_id);	}}
		
		if (!exists){	
			event.addAbsent(UserDao.find(user_id));
		}
		
		if (equ != null){
			event.removePresent(equ);
		}
				
		t.commit();
		em.close();
	}
	
	/**
	 * Zoekt alle present users op van dit event
	 */
	public static List<User> allPresent(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Event event = em.find(Event.class, id);
		List<User> users = new ArrayList<>();
		for (User u : event.getPresent()){
			users.add(u);
		}

		t.commit();
		em.close();
		return users;
	}
	
	/**
	 * Zoekt alle absent users op van dit event
	 */
	public static List<User> allAbsent(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Event event = em.find(Event.class, id);
		List<User> users = new ArrayList<>();
		for (User u : event.getAbsent()){
			users.add(u);
		}

		t.commit();
		em.close();
		return users;
	}
}
