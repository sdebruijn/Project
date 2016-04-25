package nl.project.poll;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class PollDao {

	@PersistenceContext
    private EntityManager em;	
	
	@Transactional
	public void createPoll(Poll poll){
		em.persist( poll );
	}
}
