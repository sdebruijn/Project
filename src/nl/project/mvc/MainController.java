package nl.project.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.project.team.TeamDao;
import nl.project.user.UserDao;

@Controller
@Scope("request")
public class MainController {
	
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private UserDao userDao;
	//@Autowired
	//private EventDao eventDao;	
	
	@Transactional
	@RequestMapping("/mainMenu")
	public String mainMenu(Model model) {
		model.addAttribute("teams", teamDao.all());
		return "mainMenu";
	}
	
	@RequestMapping("poll")
	public String newTeam() {
		return "Poll";
	}
	
	
}
