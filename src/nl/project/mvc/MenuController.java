package nl.project.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
	
	@RequestMapping("/mainMenu")
	public String mainMenu() {
		return "mainMenu";
	}
	
	@RequestMapping("/newTeam")
	public String newTeam() {
		return "newTeam";
	}
}
