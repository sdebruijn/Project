package nl.project.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
<<<<<<< HEAD
import javax.persistence.EntityTransaction;

import org.hibernate.Hibernate;
=======
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588

import nl.project.event.Event;
import nl.project.event.EventDao;
import nl.project.user.User;
import nl.project.user.UserDao;

@Repository
public class TeamDao {
	@PersistenceContext
    private EntityManager em;	
    
	@Autowired
	private UserDao userDao;
	@Autowired
	private EventDao eventDao;

	/**
	 * Maak een nieuw team aan en sla die op in de database
	 */	
	
	@Transactional
	public Team create(String name, String sport){
		Team team = new Team();
		team.setName(name);
		team.setSport(sport);
		em.persist( team );
		return team;
	}
	
	/**
	 * Verwijder een team uit de database
	 */
	@Transactional
	public void remove(Long id){
		Team team = em.find(Team.class, id);
		if(team != null){
			em.remove( team );
		}
	}
	
	/**
	 * Haal een team op a.d.h.v. zijn id
	 */
	@Transactional
	public Team find(Long id){
		Team team = em.find(Team.class, id);
		Hibernate.initialize(team.getMembers());
		Hibernate.initialize(team.getEvents());
		return team;
	}
	
	/**
	 * Haal alle teams op uit de database
	 */	
	@Transactional
	public List<Team> all(){
		List<Team> teams = em.createQuery("from Team", Team.class).getResultList();
		return teams;
	}
	
	/**
	 * Zoekt alle users op die bij dit team horen
	 */
	@Transactional
	public List<User> allTeamMembers(Long id){
		Team team = em.find(Team.class, id);
<<<<<<< HEAD
		Hibernate.initialize(team.getMembers());
/*		List<User> users = new ArrayList<>();
		for (User u : team.getMembers()){
			users.add(u);
		}*/

		t.commit();
		em.close();
=======
				
		Hibernate.initialize(team.getMembers());
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
		return team.getMembers();
	}
	
	/**
	 * Voegt een member toe aan team
	 */	
	@Transactional
	public void addMember(Long user_id, Long team_id){
		Team team = em.find(Team.class, team_id);
		team.addMember(userDao.findById(user_id));
	}
	
	/**
	 * Verwijdert een member van het team
	 */
	@Transactional
	public void removeMember(Long user_id, Long team_id){
		Team team = em.find(Team.class, team_id);
		team.removeMember(userDao.findById(user_id));
	}
	
	/**
	 * Verwijdert alle members van het team
	 */
	@Transactional
	public void removeAllMembers(Long team_id){
		Team team = em.find(Team.class, team_id);
		team.removeAllMembers();
	}
	
	/**
	 * Voegt een coach toe aan het team
	 */
	@Transactional
	public void addCoach(Long user_id, Long team_id){
		Team team = em.find(Team.class, team_id);
		team.setCoach(userDao.findById(user_id));
	}
	
	/**
	 * Voegt een event toe aan het team
	 */
	@Transactional
	public void addEvent (Long event_id, Long team_id){
		Team team = em.find(Team.class, team_id);
<<<<<<< HEAD
		team.addEvent(EventDao.find(event_id));
		
		t.commit();
		em.close();
=======
		team.addEvent(eventDao.find(event_id));
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
	}
	
	/**
	 * Zoekt alle events op die bij dit team horen
	 */
<<<<<<< HEAD
	public static List<Event> allEvents(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
=======
	@Transactional
	public  List<Event> allEvents(Long id){
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
		Team team = em.find(Team.class, id);

		team.sortEvents();
		List<Event> events = new ArrayList<>();
		for (Event event : team.getEvents()){
			events.add(event);
		}
<<<<<<< HEAD
		
		t.commit();
		em.close();
=======
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
		return events;
	}
}
