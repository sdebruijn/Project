package nl.project.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nl.project.user.User;
import nl.project.user.UserDao;


/*                GET         PUT             POST            DELETE
 *   Persons/	Get all 	Replace all		Create 1 new	Delete all
 *   Persons/3	Get id=3	Update id=3		DONT USE		Delete id=3
 */

@RestController
@Validated 
@RequestMapping("/api/users/")
public class UserRestApi {
	@Autowired
	private UserDao userDao;
	

	
	@RequestMapping(value = "exists/{name}/{surname}", method = RequestMethod.GET)
	@ResponseBody
	public String exists(@PathVariable String name, @PathVariable String surname)  {
		System.err.println("Inside user exist");

		
		if (userDao.exist(name, surname)) {
			System.err.println("true");
			return "true";
		} else {
			System.err.println("false");
			return "false";
		}
	}
	
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public List<User> users() {
		return userDao.all();
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		System.err.println(user);
		
		System.err.println("in POST");
		if (userDao.exist(user)) {
			return null;
		}
		
		User newUser = userDao.create(user);
		
		return newUser;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public User user(@PathVariable String id) {
		System.err.println("in GET");
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return null; 			// id is geen getal? error 404
		}
		
		User user = userDao.findById(key);
		if (user == null) {
			return null; // no user with this id? error 404
		}
		return user;
	}

	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public User edit(@RequestBody User user, @PathVariable String id) {
		System.err.println("in PUT");
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return null; // id is geen getal? error 404
		}

		User u = userDao.findById(key);
		if (u == null) { // user not found
			return null; // TODO: error message attribute to custom 404 page.
		}	
		
		if (userDao.exist(user)) {
			return null;
		}
		
		user = userDao.update(key, user);	
		return user;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return; // id is geen getal? error 404
		}

		userDao.remove(key);
		return;
	}
	
}

class FormData {
	public String name;
	public String surname;
}