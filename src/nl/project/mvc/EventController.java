package nl.project.mvc;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.project.event.Event;
import nl.project.event.EventDao;
import nl.project.team.Team;
import nl.project.team.TeamDao;

@Controller
public class EventController {

	@Autowired
	private TeamDao teamDao;
	@Autowired
	private EventDao eventDao;
	
	/**
	 * Toont bestaande events en mogelijkheid tot nieuwe events
	 */
	/**
	 * Toont teammenu
	 */
	@RequestMapping(value="/events/{id}")
	public String teamMenu(@PathVariable String id, Model model){
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
		model.addAttribute("events", team.getEvents());
		return "eventMenu";
		}
	
	/**
	 * Voegt een event toe
	 */
	@RequestMapping(value="events/creatematch", method=RequestMethod.GET)
	public String createMatch(Model model){
		model.addAttribute("defaultEvent", new Event());
		return "newEvent";
	}
	
	@RequestMapping(value="events/creatematch", method=RequestMethod.POST)
	public String createEvent(@Valid Event event, BindingResult bindingresult){
		
		if (bindingresult.hasErrors()){
			return "newEvent";
		}
		eventDao.createMatch(event);
		
		return "redirect:/mainMenu";
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		 
	     binder.registerCustomEditor(Event.class,new CustomDateEditor(dateFormat, false));   
	}
	
}
