package nl.project.mvc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.project.user.User;
import nl.project.user.UserDao;


/*                GET         PUT             POST            DELETE
 *   Persons/	Get all 	Replace all		Create 1 new	Delete all
 *   Persons/3	Get id=3	Update id=3		DONT USE		Delete id=3
 */

@RestController
@RequestMapping("/api/users/")
public class UserRestApi {
	@RequestMapping(method = RequestMethod.GET)
	public List<User> users() {
		return UserDao.all();
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public User create(@Valid User user) {
		if (UserDao.exist(user)) {
			return null;
		}
		
		User newUser = UserDao.create(user);
		
		return newUser;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public User user(@PathVariable String id) {
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return null; 			// id is geen getal? error 404
		}
		
		User user = UserDao.findById(key);
		if (user == null) {
			return null; // no user with this id? error 404
		}
		return user;
	}

	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public void edit(@PathVariable String id, @Valid User user) {
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return; // id is geen getal? error 404
		}

		User u = UserDao.findById(key);
		if (u == null) { // user not found
			return; // TODO: error message attribute to custom 404 page.
		}	
		
		if (UserDao.exist(user)) {
			return;
		}
		
		UserDao.update(key, user);	
		return;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return; // id is geen getal? error 404
		}

		UserDao.remove(key);
		return;
	}
	
}
