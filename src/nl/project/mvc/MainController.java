package nl.project.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.project.poll.Poll;
import nl.project.poll.PollDao;
import nl.project.team.TeamDao;
import nl.project.user.UserDao;

@Controller
@Scope("request")
public class MainController {
	
	@Autowired
	private TeamDao teamDao;
	
	@Transactional
	@RequestMapping("/mainMenu")
	public String mainMenu(Model model) {
		model.addAttribute("teams", teamDao.all());
		return "mainMenu";
	}
	
}
