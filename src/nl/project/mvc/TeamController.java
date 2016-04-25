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

//import nl.project.event.EventDao;
import nl.project.team.Team;
import nl.project.team.TeamDao;
import nl.project.user.User;
import nl.project.user.UserDao;

@Controller
@RequestMapping(value="team/")
public class TeamController {

	@Autowired
	private TeamDao teamDao;
	@Autowired
	private UserDao userDao;
//	@Autowired
//	private EventDao eventDao;

	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newTeam() {
		return "team/new";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String createTeam(@RequestParam String teamName, @RequestParam String sport){
		Team t = teamDao.create(teamName, sport);
		return "redirect:/team/"+t.getId();

	}
	
	/**
	 * Toont teammenu
	 */
	@RequestMapping(value="{id}")
	public String teamMenu(@PathVariable String id, Model model, HttpSession session){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		Team team = teamDao.findById(key);
		session.setAttribute("currentteam", team);
		
		model.addAttribute("team", team);
		return "team/overzicht";
		}	
	
	
	
	
	/**
	 * Toont teammanagement
	 */
	@RequestMapping(value="{id}/manage")
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
		
		Team team = teamDao.findById(key);	
		model.addAttribute("team", team);
	
		return "team/management";
	}
	
	/**
	 * Verwijdert team -- zonder om bevestiging te vragen ;)
	 * TODO: team moet eerst van users verwijderd worden
	 */
	@Transactional
	@RequestMapping(value="{id}/remove")
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
	@RequestMapping(value="{id}/members")
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
		return "team/userList";
	}
	
	/**
	 * Voegt een member toe aan het team
	 */
	@Transactional
	@RequestMapping(value="{team}/addmember/{user}")
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
		
		teamDao.addMember(key1, key2);
		return "redirect:/team/" + key2 +"/manage";
	}

	
	/**
	 * Haalt alle users van een team erbij, zodat die verwijderd kunnen worden
	 */
	@Transactional
	@RequestMapping(value="{teamId}/removeMember")
	public String showMembers(@PathVariable String teamId, Model model){
		Long key;
		try{
			key = Long.valueOf(teamId);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		model.addAttribute("action", "removeMember");
		model.addAttribute("team", key);
		model.addAttribute("users", teamDao.allTeamMembers(key));
		return "team/userList";
	}
	
	/**
	 * Verwijderd een member van een team
	 */
	@Transactional
	@RequestMapping(value="{team}/removeMember/{user}")
	public String removeMember(@PathVariable String user, @PathVariable String team){
		Long keyUser, keyTeam;
		try{
			keyUser = Long.valueOf(user);
			keyTeam = Long.valueOf(team);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		User u = userDao.findById(keyUser);
		u.setTeam(null);
		//teamDao.removeMember(keyUser, keyTeam);
		return "redirect:/team/" + keyTeam + "/manage";
	}
	
	/**
	 * Verwijderd alle members van een team
	 */
	@Transactional
	@RequestMapping(value="{teamId}/removeMembers")
	public String removeAll(@PathVariable String teamId){
		Long keyTeam;
		try{
			keyTeam = Long.valueOf(teamId);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		Team t = teamDao.findById(keyTeam);
		t.removeAllMembers();
		t.removeAllEvents();
		
		return "redirect:/team/" + keyTeam + "/manage";
	}
	
	/**
	 * Haalt alle users erbij zodat er een coach geselecteerd kan worden
	 */
	@Transactional
	@RequestMapping(value="{teamId}/coach")
	public String showCoaches(@PathVariable String teamId, Model model){
		Long key;
		try{
			key = Long.valueOf(teamId);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		model.addAttribute("action", "addcoach");
		model.addAttribute("team", key);
		model.addAttribute("users", userDao.all());
		return "team/userList";
	}
	
	/**
	 * Voegt een coach toe aan team
	 */
	@Transactional
	@RequestMapping(value="{team}/addcoach/{user}")
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
		return "redirect:/team/" + key2 + "/manage";
	}
	
}
