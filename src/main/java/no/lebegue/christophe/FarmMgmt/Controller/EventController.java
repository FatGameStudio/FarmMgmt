package no.lebegue.christophe.FarmMgmt.Controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import no.lebegue.christophe.FarmMgmt.Entity.Event;

import no.lebegue.christophe.FarmMgmt.Entity.EventRepository;

@RestController
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@GetMapping(path="/events/all")
	public @ResponseBody Iterable<Event> getAllEvent() {
		// This returns a JSON or XML with the events

		return eventRepository.findAll();
	}
	
	@RequestMapping(value="/events", method=RequestMethod.GET)
	public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start, 
										@RequestParam(value = "end", required = true) String end) {
		
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			startDate = inputDateFormat.parse(start);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad start date: " + start);
		}
		
		try {
			endDate = inputDateFormat.parse(end);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad end date: " + end);
		}
		
		return eventRepository.findByDatesBetween(startDate, endDate); 
	}
	
	@RequestMapping(value="/addevent", method=RequestMethod.POST)
	public Event addEvent(@RequestBody Event event) {
		System.out.println("appel addEvent");
		System.out.println(event.getTitle());
		System.out.println(event.getDescription());
		System.out.println(event.getStart());
		System.out.println(event.getEnd());
		System.out.println(event.getId());
		Event created = eventRepository.save(event);
		return created; 
	}

	@RequestMapping(value="/updateevent", method=RequestMethod.POST)
	public Event updateEvent(@RequestBody Event event) {
		System.out.println("appel updateEvent");
		return eventRepository.save(event);
	}

	@RequestMapping(value="/removeevent", method=RequestMethod.POST)
	public void removeEvent(@RequestBody Event event) {
		System.out.println("appel removeEvent");
		eventRepository.delete(event);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	class BadDateFormatException extends RuntimeException {
	  private static final long serialVersionUID = 1L;

		public BadDateFormatException(String dateString) {
	        super(dateString);
	    }
	}
}