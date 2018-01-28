package no.lebegue.christophe.FarmMgmt;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/test")
    public String test() {
        return "Test";
    }

    @RequestMapping("/zones")
    public List<Zone> zones() {
        return ZoneService.getAllZones();
    }
}