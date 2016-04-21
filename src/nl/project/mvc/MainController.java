package nl.project.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.project.team.Team;
import nl.project.team.TeamDao;

@Controller
public class MainController {
	
	@Autowired
	private TeamDao teamDao;
	
	@RequestMapping("/mainMenu")
	public String mainMenu(Model model) {
		model.addAttribute("teams", teamDao.all());
		return "mainMenu";
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
