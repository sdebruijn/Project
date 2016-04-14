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
			model.addAttribute("coach", team.getCoach());
			model.addAttribute("users", TeamDao.allTeamMembers(key));
			return "teamMenu";
		}
	}
	
	/**
	 * Verwijdert team -- zonder om bevestiging te vragen ;)
	 * TODO: team moet eerst van users verwijderd worden
	 */
	@RequestMapping(value="/deleteteam/{id}")
	public String deleteView(@PathVariable String id){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}

		for (User u: TeamDao.allTeamMembers(key)){
			UserDao.removeTeamFromUser(u.getId(), key);
		}
		
		TeamDao.remove(key);
		return "redirect:/mainMenu";
	}
	
	/**
	 * Haalt alle users erbij zodat member toegevoegd kan worden
	 */
	@RequestMapping(value="/showusers/{id}")
	public String showUsers(@PathVariable String id, Model model){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		model.addAttribute("action", "addmember");
		model.addAttribute("team", key);
		model.addAttribute("users", UserDao.all());
		return "userList";
	}
	
	/**
	 * Voegt een member toe aan het team
	 */
	@RequestMapping(value="/addmember/{user}/{team}")
	public String addMember(@PathVariable String user, @PathVariable String team){
		Long key1, key2;
		try{
			key1 = Long.valueOf(user);
			key2 = Long.valueOf(team);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		UserDao.addTeamToUser(key1, key2);
		return "redirect:/team/" + key2;
	}
	
	
	
	/**
	 * Haalt alle users van een team erbij, zodat die verwijderd kunnen wordn
	 */
	@RequestMapping(value="/showmembers/{id}")
	public String showMembers(@PathVariable String id, Model model){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		model.addAttribute("action", "removemember");
		model.addAttribute("team", key);
		model.addAttribute("users", TeamDao.allTeamMembers(key));
		return "userList";
	}
	
	/**
	 * Verwijderd een member van een team
	 */
	@RequestMapping(value="/removemember/{user}/{team}")
	public String removeMember(@PathVariable String user, @PathVariable String team){
		Long key1, key2;
		try{
			key1 = Long.valueOf(user);
			key2 = Long.valueOf(team);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		UserDao.removeTeamFromUser(key1, key2);
		return "redirect:/team/" + key2;
	}
	
	/**
	 * Verwijderd alle members van een team
	 */
	@RequestMapping(value="/removeall/{id}")
	public String removeAll(@PathVariable String id){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		for (User u: TeamDao.allTeamMembers(key)){
			UserDao.removeTeamFromUser(u.getId(), key);
		}
		
		return "redirect:/team/" + key;
	}
	
	/**
	 * Haalt alle users erbij zodat er een coach geselecteerd kan worden
	 */
	@RequestMapping(value="/showcoaches/{id}")
	public String showCoaches(@PathVariable String id, Model model){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		model.addAttribute("action", "addcoach");
		model.addAttribute("team", key);
		model.addAttribute("users", UserDao.all());
		return "userList";
	}
	
	/**
	 * Voegt een coach toe aan team
	 */
	@RequestMapping(value="/addcoach/{user}/{team}")
	public String addCoach(@PathVariable String user, @PathVariable String team){
		Long key1, key2;
		try{
			key1 = Long.valueOf(user);
			key2 = Long.valueOf(team);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		TeamDao.addCoach(key1, key2);
		return "redirect:/team/" + key2;
	}
	
}
