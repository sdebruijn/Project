package nl.project.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import nl.project.event.DefaultEvent;
import nl.project.event.EventDao;
import nl.project.team.Team;
import nl.project.team.TeamDao;

@Controller
public class EventController {

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
		
		Team team = TeamDao.find(key);
		model.addAttribute("team", team);
		model.addAttribute("events", TeamDao.allEvents(key));
		//model.addAttribute("events", EventDao.all());
		return "eventMenu";
		}
	
	/**
	 * Voegt een event toe
	 */
	@RequestMapping(value="events/createevent", method=RequestMethod.GET)
	public String createEvent(Model model){
		model.addAttribute("defaultEvent", new DefaultEvent());
		return "newEvent";
	}
	
/*	@RequestMapping(value="events/creatematch", method=RequestMethod.GET)
	public String createMatch(Model model){
		model.addAttribute("match", new Match());
		return "newEvent";
	}*/
	
	@RequestMapping(value="events/createevent", method=RequestMethod.POST)
	public String createEvent(@Valid DefaultEvent event, BindingResult bindingresult){
		
		if (bindingresult.hasErrors()){
			return "newEvent";
		}
		EventDao.createMatch(event);
		
		return "redirect:/mainMenu";
	}
	
}
