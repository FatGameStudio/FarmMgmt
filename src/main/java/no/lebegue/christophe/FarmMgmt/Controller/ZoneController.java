package no.lebegue.christophe.FarmMgmt.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@GetMapping(path = "/zones/add") // Map ONLY GET Requests
	public @ResponseBody String addNewZone(@RequestParam String name) {

		Zone n = new Zone();
		n.setName(name);
		zoneRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/zones/all")
	public @ResponseBody Iterable<Zone> getAllZone() {
		// This returns a JSON or XML with the users
		return zoneRepository.findAll();
	}

	@RequestMapping(value = { "/zonesList" }, method = RequestMethod.GET)
	public String zonesList(Model model) {

		model.addAttribute("zones", zoneRepository.findAll());

		Zone zone = new Zone();
		model.addAttribute("zone", zone);
		return "zonesList";
	}

	@RequestMapping(value = { "/zonesList" }, method = RequestMethod.POST)
	public String addZone(Model model, //
			@ModelAttribute("zone") Zone zone) {

		String name = zone.getName();

		if (name != null && name.length() > 0) {
			zoneRepository.save(zone);
		} else {
			model.addAttribute("errorMessage", errorMessage);
		}

		zonesList(model);

		return "zonesList";
	}

	@RequestMapping(value = { "/zonesList" }, params = { "deleteZone" })
	public String deleteZone(Model model, final HttpServletRequest req) {
		final int id = Integer.valueOf(req.getParameter("deleteZone"));
		zoneRepository.deleteById(id);
		zonesList(model);
		return "zonesList";
	}
}