package nl.project.mvc;

import javax.servlet.http.HttpSession;

<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.project.event.EventDao;
import nl.project.team.Team;
import nl.project.team.TeamDao;

@Controller
public class MainController {
	
	@Autowired
	private TeamDao teamDao;
	
	@RequestMapping("/mainMenu")
	public String mainMenu(Model model) {
<<<<<<< HEAD
		model.addAttribute("teams", TeamDao.all());
		
=======
		model.addAttribute("teams", teamDao.all());
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
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
		
<<<<<<< HEAD
		Team team = TeamDao.find(key);
=======
		Team team = teamDao.find(key);
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
		session.setAttribute("currentteam", team);
		
		model.addAttribute("team", team);
		return "teamMenu";
		}	
}
