package no.lebegue.christophe.FarmMgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class FarmMgmtApplication extends SpringBootServletInitializer {

	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FarmMgmtApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(FarmMgmtApplication.class, args);
	}
	
       
}