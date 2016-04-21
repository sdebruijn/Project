package nl.project.event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

<<<<<<< HEAD
import nl.project.mvc.EntityManagerManager;
import nl.project.team.Team;
import nl.project.user.User;
import nl.project.user.UserDao;
=======
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588

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
<<<<<<< HEAD
	public static void createMatch(Event event){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
=======
	@Transactional
	public void createMatch(Event event){
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
		em.persist( event );
	}
	
	/**
	 * Haal alle events op uit de database
	 */	
<<<<<<< HEAD
	public static List<Event> all(){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<Event> events = em.createQuery("from Event", Event.class).getResultList();
		t.commit();
		em.close();
=======
	@Transactional
	public List<Event> all(){
		List<Event> events = em.createQuery("from Event", Event.class).getResultList();
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
		return events;
	}
	
	/**
	 * Haal een event op a.d.h.v. zijn id
	 */
<<<<<<< HEAD
	public static Event find(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Event event = em.find(Event.class, id);
		t.commit();
		em.close();
=======
	@Transactional
	public Event find(Long id){
		Event event = em.find(Event.class, id);
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
		return event;
	}
	
	/**
<<<<<<< HEAD
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
=======
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
	
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
}
