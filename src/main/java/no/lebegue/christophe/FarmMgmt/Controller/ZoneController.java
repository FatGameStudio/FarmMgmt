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

import no.lebegue.christophe.FarmMgmt.Entity.Zone;
import no.lebegue.christophe.FarmMgmt.Entity.ZoneRepository;

@Controller
public class ZoneController {
	@Autowired
	private ZoneRepository zoneRepository;

	@Value("${error.message}")
	private String errorMessage;

	
	@RequestMapping(value = { "/adminZones" }, method = RequestMethod.GET)
	public String zonesList(Model model) {

		model.addAttribute("zones", zoneRepository.findAll());

		Zone zone = new Zone();
		model.addAttribute("newZone", zone);
		return "adminZones";
	}

	@RequestMapping(value = { "/adminZones" }, params = { "addZone" }, method = RequestMethod.POST)
	public String addZone(Model model, //
			@ModelAttribute("newZone") Zone newZone) {

		String name = newZone.getName();

		if (name != null && name.length() > 0) {
			zoneRepository.save(newZone);
		} else {
			model.addAttribute("errorMessage", errorMessage);
		}

		zonesList(model);

		return "adminZones";
	}

	@RequestMapping(value = { "/adminZones" }, params = { "deleteZone" }, method = RequestMethod.POST)
	public String deleteZone(Model model, final HttpServletRequest req) {
		final int id = Integer.valueOf(req.getParameter("deleteZone"));
		zoneRepository.deleteById(id);
		zonesList(model);
		return "adminZones";
	}
	
}