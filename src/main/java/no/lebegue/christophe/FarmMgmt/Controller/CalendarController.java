package no.lebegue.christophe.FarmMgmt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import no.lebegue.christophe.FarmMgmt.Entity.ActivityRepository;
import no.lebegue.christophe.FarmMgmt.Entity.ZoneRepository;

@Controller 
public class CalendarController {
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ZoneRepository zoneRepository;
	
	@RequestMapping(value="/jsonCalendar", method=RequestMethod.GET)
	public ModelAndView jsonCalendar(Model model) {
		model.addAttribute("allActivities", activityRepository.findAll());
		model.addAttribute("allZones", zoneRepository.findAll());
		
		return new ModelAndView("jsoncalendar");
		
	}
	
}