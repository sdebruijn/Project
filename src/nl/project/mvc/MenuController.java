package nl.project.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.project.team.Team;
import nl.project.team.TeamDao;
import nl.project.user.User;
import nl.project.user.UserDao;

@Controller
public class MenuController {
	
	@RequestMapping("/mainMenu")
	public String mainMenu(Model model) {
		model.addAttribute("teams", TeamDao.all());
		return "mainMenu";
	}
	
	@RequestMapping("/newTeam")
	public String newTeam() {
		return "newTeam";
	}
	
	@RequestMapping(value = "/createTeam", method = RequestMethod.POST)
	public String createTeam(@RequestParam String teamName, @RequestParam String sport){
/*		UserDao.create("Looise", "Sander");
		UserDao.create("de Bruijn", "Sijmen");
		UserDao.create("Reindert", "Reindert");*/
		TeamDao.create(teamName, sport);
		

		
		return "redirect:/mainMenu";
	}
	
	/**
	 * Toont de teampagina
	 */
	@RequestMapping(value="/team/{id}")
	public String detailView(@PathVariable String id, Model model){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		Team team = TeamDao.find(key);
		
		if(team == null){
			// geen rit met gegeven id? error 404
			return null;
		} else {
			model.addAttribute("team", team);
			model.addAttribute("users", UserDao.all());
			return "teamMenu";
		}
	}
	
	/**
	 * Verwijdert team -- zonder om bevestiging te vragen ;)
	 */
	@RequestMapping(value="/delete/{id}")
	public String deleteView(@PathVariable String id){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}

		TeamDao.remove(key);
		return "redirect:/mainMenu";
	}
	
	/**
	 * Voegt een member toe
	 */
	@RequestMapping(value="/addmember/{id}")
	public String addMember(@PathVariable String id){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}

		System.out.println(key);
		UserDao.find(1l).setTeam(TeamDao.find(key));
		return "redirect:/mainMenu";
	}
}
