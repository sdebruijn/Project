package nl.project.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nl.project.event.DefaultEvent;
import nl.project.event.EventDao;
import nl.project.mvc.EntityManagerManager;
import nl.project.user.User;
import nl.project.user.UserDao;

public abstract class TeamDao {
	
	/**
	 * Maak een nieuw team aan en sla die op in de database
	 */	
	public static Team create(String name, String sport){
		Team team = new Team();
		team.setName(name);
		team.setSport(sport);
		team.setMembers(new ArrayList<>());
		team.setEvents(new ArrayList<>());
		
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist( team );
		t.commit();
		em.close();
		
		return team;
	}
	
	/**
	 * Verwijder een team uit de database
	 */
	public static void remove(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Team team = em.find(Team.class, id);
		if(team != null){
			em.remove( team );
		}
		t.commit();
		em.close();
	}
	
	/**
	 * Haal een team op a.d.h.v. zijn id
	 */
	public static Team find(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Team team = em.find(Team.class, id);
		t.commit();
		em.close();
		return team;
	}
	
	/**
	 * Haal alle teams op uit de database
	 */	
	public static List<Team> all(){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<Team> teams = em.createQuery("from Team", Team.class).getResultList();
		t.commit();
		em.close();
		return teams;
	}
	
	/**
	 * Zoekt alle users op die bij dit team horen
	 */
	public static List<User> allTeamMembers(Long id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Team team = em.find(Team.class, id);
		List<User> users = new ArrayList<>();
		for (User u : team.getMembers()){
			users.add(u);
		}

		t.commit();
		em.close();
		return users;
	}
	
	/**
	 * Voegt een member toe aan team
	 */	
	public static void addMember(Long user_id, Long team_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Team team = em.find(Team.class, team_id);
		team.addMember(UserDao.findById(user_id));
		
		t.commit();
		em.close();
	}
	
	/**
	 * Verwijdert een member van het team
	 */
	public static void removeMember(Long user_id, Long team_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Team team = em.find(Team.class, team_id);
		team.removeMember(UserDao.findById(user_id));

		t.commit();
		em.close();
	}
	
	/**
	 * Verwijdert alle members van het team
	 */
	public static void removeAllMembers(Long team_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Team team = em.find(Team.class, team_id);
		team.removeAllMembers();

		t.commit();
		em.close();
	}
	
	
	/**
	 * Voegt een coach toe aan het team
	 */
	public static void addCoach(Long id, Long teamId){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Team team = em.find(Team.class, teamId);
		team.setCoach(UserDao.findById(id));
		t.commit();
		em.close();
	}
	
	/**
	 * Voegt een event toe aan het team
	 */
	public static void addEvent (Long event_id, Long team_id){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Team team = em.find(Team.class, team_id);
		List<DefaultEvent> events = new ArrayList<>();
		for (DefaultEvent e : team.getEvents()){
			events.add(e);
		}
		events.add(EventDao.find(event_id));
		team.setEvents(events);
		em.persist(team);
		
		t.commit();
		em.close();
	}
}
