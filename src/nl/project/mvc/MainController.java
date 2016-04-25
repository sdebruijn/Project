package nl.project.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.project.team.Team;
import nl.project.team.TeamDao;

@Controller
public class MainController {
	
	@Autowired
	private TeamDao teamDao;
	
	//@Autowired
	//private UserDao userDao;
	//@Autowired
	//private EventDao eventDao;
	
	
	@RequestMapping(value = "/poll", method = RequestMethod.GET)
	public @ResponseBody List<String> pollGet (){
		System.out.println("HALLO");
		List<String> list = new ArrayList<>();
		list.add("Hallo");
		list.add("Bla");
		return list;
	}
	
	@RequestMapping(value = "/poll", method = RequestMethod.POST)
	public @ResponseBody String pollPost (){
		System.out.println("HAaai");
		List<String> list = new ArrayList<>();
		list.add("Hallo");
		list.add("Bla");
		return "Hoi";
	}
	
	@Transactional
	@RequestMapping("/mainMenu")
	public String mainMenu(Model model) {
		model.addAttribute("teams", teamDao.all());

		return "Poll";
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
