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
@RequestMapping(value="/user")
public class UserController {
	
	@RequestMapping(value="resttest", method=RequestMethod.GET)
	public String testpage() {
		return "overzicht";
	}
	
	@RequestMapping(value="testpage", method=RequestMethod.GET)
	public String testpage(Model model) {
		
		User u = new User("Sijmen","de Bruijn");
		model.addAttribute("u", u);
		
		if (UserDao.exist(u)) {
			model.addAttribute("result", "user already exists");
		} else {
			model.addAttribute("result", "new user!");
		}

		model.addAttribute("user", u);
		return "testpage";
	}

	
	/**
	 * Input new user page
	 */
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newUser(Model model){
		model.addAttribute("user", new User()); 
		return "user/new";
	}
	
	/**
	 * Create new user and redirect
	 */
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String newUser(@Valid User user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "user/new";
		}
		if (UserDao.exist(user)) {
			model.addAttribute("error", "This name and surname is already recorded, please enter something else.");
			return "user/new";
		}
		
		
		UserDao.create(user);
		
		return "redirect:/mainMenu";
	}
	
	/**
	 *  Edit user page
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String changeUser(@PathVariable String id, Model model) {
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return null; // id is geen getal? error 404
		}
		
		User user = UserDao.findById(key);
		if (user == null) { // user not found
			return null; // TODO: error message attribute to custom 404 page.
		}
		
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	/**
	 *  Edit user in database and redirect
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String changeUser(@PathVariable String id, @Valid User user, BindingResult bindingResult, Model model) {
		/*
		 * Check 
		 *  - is key a number?
		 *  - is user (name/surname) already in system? Except for the user with current id.
		 *  - is key used in database?
		 *  - are the input fields valid?
		 */
		Long key;
		try {
			key = Long.valueOf(id);
		} catch(NumberFormatException e){
			return null; // id is geen getal? error 404
		}

		User u = UserDao.findById(key);
		if (u == null) { // user not found
			return null; // TODO: error message attribute to custom 404 page.
		}	
		
		if (UserDao.exist(user)) {
			model.addAttribute("error", "This name and surname are already in use, please enter something else.");
			return "user/edit";
		}
		
		if (bindingResult.hasErrors()) {
			return "user/edit"; // Show errors in form
		}
	
		UserDao.update(key, user);
		return "redirect:/mainMenu";
	}
}