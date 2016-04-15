package nl.project.mvc;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.project.user.User;
import nl.project.user.UserDao;

@Controller
public class UserController {
	
	/**
	 * Input new user page
	 */
	@RequestMapping(value={"/newUser","/newuser"}, method=RequestMethod.GET)
	public String newUser(Model model){
		model.addAttribute("user", new User()); 
		return "newUser";
	}
	
	/**
	 * Create new user and redirect
	 */
	@RequestMapping(value={"/newUser","/newuser"}, method=RequestMethod.POST)
	public String newUser(@Valid User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "newUser";
		}
		UserDao.create(user);
		
		return "redirect:/mainMenu";
	}
	
	/**
	 *  Change user page
	 */
	@RequestMapping(value="/changeUser/{id}", method=RequestMethod.GET)
	public String changeUser(@PathVariable String id, Model model) {
		Long key;
		try {
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		User user = UserDao.findById(key);
		if (user == null) { // user not found
			// TODO: error message attribute to custom 404 page.
			return null;
		}
		
		model.addAttribute("user", user);
		
		return "changeUser";
		
	}
	/**
	 *  Change user and redirect
	 */
	@RequestMapping(value="/changeUser/{id}", method=RequestMethod.POST)
	public String changeUser(@PathVariable String id, @Valid User user, BindingResult bindingResult) {
		Long key;
		try {
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		if (bindingResult.hasErrors()) {
			return "changeUser";
		}
		
		UserDao.update(key, user);
		return "redirect:/mainMenu";
	}
}