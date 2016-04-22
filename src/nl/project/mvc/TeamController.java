package nl.project.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//import nl.project.event.EventDao;
import nl.project.team.Team;
import nl.project.team.TeamDao;
import nl.project.user.UserDao;

@Controller
public class TeamController {

	@Autowired
	private TeamDao teamDao;
	@Autowired
	private UserDao userDao;
//	@Autowired
//	private EventDao eventDao;

	
	/**
	 * Toont teammanagement
	 */
	@RequestMapping(value="/team/{id}")
	@Transactional
	public String teamManagement(@PathVariable String id, Model model){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		Team team = teamDao.find(key);	
		model.addAttribute("team", team);
	
		return "teamManagement";
	}
	
	/**
	 * Verwijdert team -- zonder om bevestiging te vragen ;)
	 * TODO: team moet eerst van users verwijderd worden
	 */
	@Transactional
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

		teamDao.remove(key);
		return "redirect:/mainMenu";
	}
	
	/**
	 * Haalt alle users erbij zodat member toegevoegd kan worden
	 */
	@Transactional
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
		model.addAttribute("users", userDao.all());
		return "userList";
	}
	
	/**
	 * Voegt een member toe aan het team
	 */
	@Transactional
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
		
		teamDao.addMember(userDao.findById(key1), teamDao.find(key2));
		return "redirect:/team/" + key2;
	}
	
	
	
	/**
	 * Haalt alle users van een team erbij, zodat die verwijderd kunnen worden
	 */
	@Transactional
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
		model.addAttribute("users", teamDao.allTeamMembers(key));
		return "userList";
	}
	
	/**
	 * Verwijderd een member van een team
	 */
	@Transactional
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
		
		teamDao.removeMember(key1, key2);
		return "redirect:/team/" + key2;
	}
	
	/**
	 * Verwijderd alle members van een team
	 */
	@Transactional
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
		
		teamDao.removeAllMembers(key);
		
		return "redirect:/team/" + key;
	}
	
	/**
	 * Haalt alle users erbij zodat er een coach geselecteerd kan worden
	 */
	@Transactional
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
		model.addAttribute("users", userDao.all());
		return "userList";
	}
	
	/**
	 * Voegt een coach toe aan team
	 */
	@Transactional
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
		
		teamDao.addCoach(key1, key2);
		return "redirect:/team/" + key2;
	}
	
}
