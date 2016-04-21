package nl.project.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nl.project.user.UserDao;

@Repository
public class EventDao {
	@PersistenceContext
    private EntityManager em;	
    
	@Autowired
	private UserDao userDao;
	
	/**
	 * Maak een nieuwe wedstrijd aan en sla die op in de database
	 */	
	@Transactional
	public void createMatch(Event event){
		em.persist( event );
	}
	
	/**
	 * Haal alle events op uit de database
	 */	
	@Transactional
	public List<Event> all(){
		List<Event> events = em.createQuery("from Event", Event.class).getResultList();
		return events;
	}
	
	/**
	 * Haal een event op a.d.h.v. zijn id
	 */
	@Transactional
	public Event find(Long id){
		Event event = em.find(Event.class, id);
		return event;
	}
	
	/**
	 * Voegt een present user toe aan het event
	 */
	@Transactional
	public void addPresent (Long event_id, Long user_id){
		Event event = em.find(Event.class, event_id);
		event.addPresent(userDao.findById(user_id));
	}
	
	/**
	 * Voegt een absent user toe aan het event
	 */
	@Transactional
	public void addAbsent (Long event_id, Long user_id){
		Event event = em.find(Event.class, event_id);
		event.addAbsent(userDao.findById(user_id));
	}
	
}
