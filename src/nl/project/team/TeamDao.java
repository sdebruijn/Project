package nl.project.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
				
		Hibernate.initialize(team.getMembers());
		return team.getMembers();
	}
	
	/**
	 * Voegt een member toe aan team
	 */	
	@Transactional
	public void addMember(User user, Team team){
		System.out.println(team.getMembers());
		user.setTeam(team);
		System.out.println(team.getMembers());
		em.persist(user);
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
		team.addEvent(eventDao.find(event_id));
	}
	
	/**
	 * Zoekt alle events op die bij dit team horen
	 */
	@Transactional
	public  List<Event> allEvents(Long id){
		Team team = em.find(Team.class, id);

		team.sortEvents();
		List<Event> events = new ArrayList<>();
		for (Event event : team.getEvents()){
			events.add(event);
		}
		return events;
	}
}
