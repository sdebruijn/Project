package nl.project.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nl.project.mvc.EntityManagerManager;
import nl.project.team.Team;

public abstract class EventDao {
	
	/**
	 * Maak een nieuwe wedstrijd aan en sla die op in de database
	 */	
	public static void createMatch(DefaultEvent event){
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
	public static List<DefaultEvent> all(){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<DefaultEvent> events = em.createQuery("from DefaultEvent", DefaultEvent.class).getResultList();
		t.commit();
		em.close();
		return events;
	}
	
	/**
	 * Haal een event op a.d.h.v. zijn id
	 */
	public static DefaultEvent find(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		DefaultEvent event = em.find(DefaultEvent.class, id);
		t.commit();
		em.close();
		return event;
	}
	
}
