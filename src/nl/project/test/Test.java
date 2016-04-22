package nl.project.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import nl.project.team.Team;
import nl.project.user.User;

public class Test {

	
	
	public static void main(String[] args) {
		User u = new User("Player", "Achternaam");
		User v = new User("Player", "de Boer");
		User c = new User("Coach", "bla");
		User m = new User("Manager", "bla");
		Team t = new Team();
		t.setName("Silly names");
		t.setManager(m);
		t.setCoach(c);


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teamapp");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			em.persist(c);
			em.persist(m);
			em.persist(t);
			
			u.setTeam(t);
			v.setTeam(t);
			
			em.persist(u);
			em.persist(v);
			
			Long tid = t.getId();
		
		tx.commit();
		em.close();
		
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		tx2.begin();		
			Team ttt = em2.find(Team.class, tid);
			ttt.getMembers().size();
			ttt.getCoach().toString();
			ttt.getManager().toString();
		tx2.commit();
		em2.close();
		
		System.out.println(ttt.getName());
		System.out.println(ttt.getCoach());
		System.out.println(ttt.getManager());
		System.out.println(ttt.getMembers());
		
		
		
	}
	
	
	
	
	public static void lala(String[] args) {
		User u = new User("Player", "Achternaam");
		User v = new User("Player", "de Boer");
		User c = new User("Coach", "bla");
		Team t = new Team();
		t.setName("Silly names");
		t.setCoach(c);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teamapp");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();		
			em.persist(c);
			em.persist(t);
		tx.commit();
		em.close();
		
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		tx2.begin();		
			u.setTeam(t);
			v.setTeam(t);
			em2.persist(u);
			em2.persist(v);
			Long id = t.getId();
			Long uid = u.getId();
		tx2.commit();
		em2.close();
		
		EntityManager em3 = emf.createEntityManager();
		EntityTransaction tx3 = em3.getTransaction();
		tx3.begin();		
		
			User uFound = em3.find(User.class, uid);
			//uFound.setTeam(null);
	
		tx3.commit();
		em3.close();
		
		EntityManager em4 = emf.createEntityManager();
		EntityTransaction tx4 = em4.getTransaction();
		tx4.begin();
			uFound = em4.find(User.class, uid);
		
			Team teamFound = em4.find(Team.class, id);
			teamFound.setCoach(uFound);
			List<User> userlijst = teamFound.getMembers();

			userlijst.get(0);
		
		tx4.commit();
		em4.close();
		
		System.out.println(teamFound.getName());
		System.out.println(userlijst);
		System.out.println(teamFound.getMembers() );
		
		
		emf.close();
		

	}

}
