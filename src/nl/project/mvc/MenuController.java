package nl.project.mvc;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.project.team.Team;
import nl.project.team.TeamDao;

@Controller
public class MenuController {
	
	ArrayList<Team> teams = new ArrayList<>();
	
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
	public String createTeam(@RequestParam String teamName, Model model){
		TeamDao.create(teamName);
		model.addAttribute("teams", TeamDao.all());
		return "mainMenu";
	}
}
