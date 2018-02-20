package no.lebegue.christophe.FarmMgmt.Controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.lebegue.christophe.FarmMgmt.Entity.Activity;
import no.lebegue.christophe.FarmMgmt.Entity.ActivityRepository;

@Controller
public class ActivityController {
	@Autowired
	private ActivityRepository activityRepository;

	@Value("${error.message}")
	private String errorMessage;
	
	@RequestMapping(value = { "/adminActivities" }, method = RequestMethod.GET)
	public String adminActivities(Model model) {

		model.addAttribute("activities", activityRepository.findAll());

		Activity activity = new Activity();
		model.addAttribute("newActivity", activity);
		return "adminActivity";
	}

	@RequestMapping(value = { "/adminActivities" }, params = { "addActivity" }, method = RequestMethod.POST)
	public String addActivity(Model model, //
			@ModelAttribute("newZone") Activity newZone) {

		String name = newZone.getName();

		if (name != null && name.length() > 0) {
			activityRepository.save(newZone);
		} else {
			model.addAttribute("errorMessage", errorMessage);
		}

		adminActivities(model);

		return "adminActivity";
	}

	@RequestMapping(value = { "/adminActivities" }, params = { "deleteActivity" }, method = RequestMethod.POST)
	public String deleteActivity(Model model, final HttpServletRequest req) {
		final int id = Integer.valueOf(req.getParameter("deleteActivity"));
		activityRepository.deleteById(id);
		adminActivities(model);
		return "adminActivity";
	}
	
}