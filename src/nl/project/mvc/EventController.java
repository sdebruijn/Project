package nl.project.mvc;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import nl.project.user.User;
import nl.project.user.UserDao;

@Controller
public class EventController {

	/**
	 * Toont bestaande events en mogelijkheid tot nieuwe events
	 */

	@RequestMapping(value="/events")
	public String eventMenu(Model model, HttpSession session){
		
		Team team = (Team) session.getAttribute("currentteam");
		
		if (team == null){
			return "redirect:/mainMenu/";
		}
		
		model.addAttribute("team", team);
		model.addAttribute("events", TeamDao.allEvents(team.getId()));
		return "eventMenu";
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
		
		Event event = EventDao.find(key);
		session.setAttribute("currentevent", event);
		
		List<User> present = EventDao.allPresent(event.getId());
		List<User> absent = EventDao.allAbsent(event.getId());
		model.addAttribute("event",event);
		model.addAttribute("present",present);
		model.addAttribute("absent", absent);
		
		model.addAttribute("p", false);
		model.addAttribute("a", false);
		
		return "eventDetail";
		}	
	
	
	/**
	 * Voegt een event toe
	 */
	@RequestMapping(value="events/createevent", method=RequestMethod.GET)
	public String createEvent(Model model, HttpSession session){
		model.addAttribute("event", new Event());
		model.addAttribute("team", session.getAttribute("currentteam"));
		return "newEvent";
	}
	
	
	@RequestMapping(value="events/createevent", method=RequestMethod.POST)
	public String createEvent(@Valid Event event, BindingResult bindingresult, HttpSession session){
		
		if (bindingresult.hasErrors()){
			return "newEvent";
		}
		
		EventDao.createMatch(event);
		
		Team team = (Team) session.getAttribute("currentteam");
		TeamDao.addEvent(event.getId(), team.getId());
		
		return "redirect:/events/" + team.getId();
	}
	
	/**
	 * Zodra er op present gedrukt wordt, wordt de huidige user toegevoegd aan die lijst. Daarna terug naar eventdetail
	 */
	@RequestMapping(value="/events/present")
	public String present (HttpSession session, Model model){

		Event event = (Event) session.getAttribute("currentevent");
		
		if (event == null){
			return "redirect:/mainMenu/";
		}
		
		User user = UserDao.findById(1l);
		
		EventDao.addPresent(event.getId(), user.getId());

		List<User> present = EventDao.allPresent(event.getId());
		List<User> absent = EventDao.allAbsent(event.getId());
		model.addAttribute("event",event);
		model.addAttribute("present",present);
		model.addAttribute("absent", absent);
		
		model.addAttribute("p", true);
		model.addAttribute("a", false);
		return "eventDetail";
	}
	
	/**
	 * Zodra er op absent gedrukt wordt, wordt de huidige user toegevoegd aan die lijst. Daarna terug naar eventdetail
	 */
	@RequestMapping(value="/events/absent")
	public String absent (HttpSession session, Model model){

		Event event = (Event) session.getAttribute("currentevent");
		
		if (event == null){
			return "redirect:/mainMenu/";
		}
		
		User user = UserDao.findById(1l);
		
		EventDao.addAbsent(event.getId(), user.getId());
		
		List<User> present = EventDao.allPresent(event.getId());
		List<User> absent = EventDao.allAbsent(event.getId());
		model.addAttribute("event",event);
		model.addAttribute("present",present);
		model.addAttribute("absent", absent);
		model.addAttribute("p", false);
		model.addAttribute("a", true);
		return "eventDetail";
	}
	
}
