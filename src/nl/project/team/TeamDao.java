package nl.project.team;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class TeamDao {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teams");
	
	/**
	 * Maak een nieuw team aan en sla die op in de database
	 */
	
	public static Team create(String name){
		Team team = new Team();
		team.setName(name);
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist( team );
		t.commit();
		em.close();
		
		return team;
	}
	
	/**
	 * Haal alle teams op uit de database
	 */
	public static List<Team> all(){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		List<Team> teams = em.createQuery("from Team", Team.class).getResultList();
		t.commit();
		em.close();
		return teams;
	}
	

}
