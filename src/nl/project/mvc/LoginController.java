package nl.project.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.project.user.User;
import nl.project.user.UserDao;

@Controller
@Scope("request")
public class LoginController {
	@Autowired
	private UserDao userDao;
	//@Autowired
	//private EventDao eventDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession  session, User user) {
		User curUser = userDao.find(user.getName(), user.getSurname());
		session.setAttribute("curUser",curUser);
		return "redirect:/mainMenu";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
