package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@RequestMapping("/")
	public String index() {
		log.info("just clicked on home page");
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/hello")
	public String hello() {
		log.warn("just clicked on hello page");
		return "Greetings from Spring Boot! 2";
	}

	@RequestMapping("/date")
	public String getDate() {
		
		log.error("just clicked on date page");
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:MM/DD");
		
		return "hello it's : " + sdf.format(now);
	}
}
