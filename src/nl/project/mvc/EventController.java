package nl.project.mvc;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(value="/events")
	public String eventMenu(Model model, HttpSession session){
		
		Team team = (Team) session.getAttribute("currentteam");
		model.addAttribute("team", team);
		model.addAttribute("events", teamDao.allEvents(team.getId()));
		return "event/menu";
	}
	
	/**
	 * Toont eventdetails
	 */
	
	@RequestMapping(value="/events/{id}")
	public String eventDetail(@PathVariable String id, Model model, HttpSession session){
		Long key;
		try{
			key = Long.valueOf(id);
		}
		catch(NumberFormatException e){
			// id is geen getal? error 404
			return null;
		}
		
		Event event = eventDao.find(key);
		session.setAttribute("currentevent", event);
		model.addAttribute("event", event);
		return "details";
		}	
	
	
	/**
	 * Voegt een event toe
	 */
	@RequestMapping(value="events/new", method=RequestMethod.GET)
	public String createEvent(Model model, HttpSession session){
		model.addAttribute("event", new Event());
		model.addAttribute("team", session.getAttribute("currentteam"));
		return "event/new";
	}
	
	@RequestMapping(value="events/new", method=RequestMethod.POST)
	public String createEvent(@Valid Event event, BindingResult bindingresult, HttpSession session){
		
		if (bindingresult.hasErrors()){
			return "event/new";
		}
		
		eventDao.createMatch(event);
		
		Team team = (Team) session.getAttribute("currentteam");
		teamDao.addEvent(event.getId(), team.getId());
		
		return "redirect:/events/";
	}
	
	/*
	@RequestMapping(value="/events/present")
	public String present (HttpSession session, Model model){

		Event event = (Event) session.getAttribute("currentevent");
		User user = userDao.findById(1l);
		
		eventDao.addPresent(event.getId(), user.getId());
		model.addAttribute("event",event);
		return "eventDetail";
	}
	
	@RequestMapping(value="/events/absent")
	public String absent (HttpSession session, Model model){

		Event event = (Event) session.getAttribute("currentevent");
		User user = userDao.findById(1l);
		
		eventDao.addAbsent(event.getId(), user.getId());
		model.addAttribute("event",event);
		return "eventDetail";*/


}
