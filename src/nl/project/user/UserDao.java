package nl.project.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {
	/**
	 * Spring provides an Entitymanager at runtime.
	 */
    @PersistenceContext
    private EntityManager em;
	
	/**
	 * Maak een nieuw user aan en sla die op in de database
	 */
    @Transactional
	public User create(String name, String surname) {
		User user = new User(name, surname);
		return create(user);
	}

	@Transactional
	public User create(User user) {
		if (user != null) {
			em.persist(user);
		}
		return user;
	}

	/**
	 * Finds a user based on name and surname
	 * @param user or (name, surname)
	 * @return user
	 */
	@Transactional
	public User find(User user) {
		return find(user.getName(), user.getSurname());
	}

	@Transactional
	public User find(String name, String surname) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.name=:name AND u.surname=:surname");
		return (User) q.setParameter("name", name)
                       .setParameter("surname", surname)
				       .getSingleResult();
	}

	/**
	 * Find user by id
	 * @param id
	 * @return user
	 */
	@Transactional
	public User findById(Long id) {
		return em.find(User.class, id);
	}
	
	/**
	 * Remove user from database based on id
	 * @param id
	 * @return void
	 */
	@Transactional
	public void remove(Long id) {
		User user = em.find(User.class, id);
		if (user != null) {
			em.remove(user);
		}
		
	}
	
	/**
	 * Get all users from database
	 */
	@Transactional
	public List<User> all() {
		List<User> ret =  em.createQuery("from User", User.class).getResultList();
		for( User u : ret) {
			u.getTeams();
		}		
		return ret;
	}

	/**
	 * Update een user adhv id
	 */
	@Transactional
	public User update(Long id, User userUpdate) {
		if (userUpdate == null) {
			throw new IllegalStateException("Updated user cannot be null");
		}
		if (userUpdate.getName() == null) {
			throw new IllegalStateException("Updated user has a name with value null");
		} 
		if (userUpdate.getSurname() == null) {
			throw new IllegalStateException("Updated user has a surname with value null");
		} 
		
		User user = em.find(User.class, id);
		if (user == null) {
			throw new IllegalStateException("User with id:"+id+"does not exist");
		}
		
		user.setName( userUpdate.getName());
		user.setSurname( userUpdate.getSurname());
		return user;
		
	}
	
	/**
	 * Returns true if a user exists. 
	 */
	@Transactional
	public boolean exist(User user) {
		Query q = em.createQuery("SELECT count(u) FROM User u WHERE u.name=:name AND u.surname=:surname AND NOT u.id=:id");
		q.setParameter("name", user.getName());
		q.setParameter("surname", user.getSurname());
		q.setParameter("id", user.getId() == null ? 0 : user.getId() );
		
		Long count = (Long) q.getSingleResult();
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
