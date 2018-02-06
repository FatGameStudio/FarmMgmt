package no.lebegue.christophe.FarmMgmt.Controller;



import no.lebegue.christophe.FarmMgmt.Entity.Activity;
import no.lebegue.christophe.FarmMgmt.Entity.ActivityRepository;
import no.lebegue.christophe.FarmMgmt.Entity.Task;
import no.lebegue.christophe.FarmMgmt.Entity.TaskRepository;
import no.lebegue.christophe.FarmMgmt.Entity.Zone;
import no.lebegue.christophe.FarmMgmt.Entity.ZoneRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@Autowired 
	private TaskRepository taskRepository;
	@Autowired 
	private ActivityRepository activityRepository;
	@Autowired 
	private ZoneRepository zoneRepository;

	// inject via application.properties
	@Value("${welcome.message}")
	private String message;
	 
	@RequestMapping("/")
	public String welcome2(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
    
    
	
    @RequestMapping("/test")
    public @ResponseBody String test() {
    	String test ="";
    	
    	for (Zone zone : zoneRepository.findAll()) {
			test += zone.toString()+"<br>";
		}
    	test += "<br>";
    	
    	for (Activity activity : activityRepository.findAll()) {
			test += activity.toString()+"<br>";
		}
    	test += "<br>";
    	
    	for (Task task : taskRepository.findAll()) {
			test += task.toString()+"<br>";
		}
    	test += "<br>";
    	
        return  test;
    }

}