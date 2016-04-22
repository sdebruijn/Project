package nl.project.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.project.event.Event;
import nl.project.event.EventDao;
import nl.project.team.Team;
import nl.project.team.TeamDao;
import nl.project.user.User;
import nl.project.user.UserDao;

@Controller
public class MainController {
	
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EventDao eventDao;
	
	
	@Transactional
	@RequestMapping("/mainMenu")
	public String mainMenu(Model model) {
		//model.addAttribute("teams", teamDao.all());
		teamDao.create("Sport", "Sport");
		teamDao.create("Sposdrt", "Sport");
		userDao.create(new User("Fiets", "Fiets"));
		Event e = new Event();
		e.setTitle("Woohoo");
		eventDao.createMatch(e);
		
		
		Team t = teamDao.find(1l);
		Team p = teamDao.find(2l);
		User u = userDao.findById(1l);
		teamDao.addMember(u, t);
		System.out.println("Team t:" + teamDao.allTeamMembers(t.getId()));
		System.out.println("Team p:" + teamDao.allTeamMembers(p.getId()));
		teamDao.addMember(u, p);
		
		System.out.println("Team t:" + teamDao.allTeamMembers(t.getId()));
		System.out.println("Team p:" + teamDao.allTeamMembers(p.getId()));
		System.out.println(u.getTeam());
		
		return "test";
	}
	
	@RequestMapping("/newTeam")
	public String newTeam() {
		return "newTeam";
	}
	
	@RequestMapping(value = "/createTeam", method = RequestMethod.POST)
	public String createTeam(@RequestParam String teamName, @RequestParam String sport){
		teamDao.create(teamName, sport);
		return "redirect:/mainMenu";

	}
	
	/**
	 * Toont teammenu
	 */
	@RequestMapping(value="/teammenu/{id}")
	public String teamMenu(@PathVariable String id, Model model, HttpSession session){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		Team team = teamDao.find(key);
		session.setAttribute("currentteam", team);
		
		model.addAttribute("team", team);
		return "teamMenu";
		}	
}
