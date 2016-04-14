package nl.project.event;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class EventDao {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("teamapp");
	
	/**
	 * Maak een nieuwe wedstrijd aan en sla die op in de database
	 */	
	public static Event createMatch(Event event){
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist( event );
		t.commit();
		em.close();
		
		return event;
	}
	
}
