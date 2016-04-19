package nl.project.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nl.project.mvc.EntityManagerManager;
import nl.project.team.Team;
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
	 * Voegt een present user toe aan het event
	 */
	public static void addPresent (Long event_id, Long user_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Event event = em.find(Event.class, event_id);
		event.addPresent(UserDao.find(user_id));
				
		t.commit();
		em.close();
	}
	
	/**
	 * Voegt een absent user toe aan het event
	 */
	public static void addAbsent (Long event_id, Long user_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Event event = em.find(Event.class, event_id);
		event.addAbsent(UserDao.find(user_id));
				
		t.commit();
		em.close();
	}
	
}
