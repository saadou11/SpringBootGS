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

	/**
	 * fonction liée a la requete http '/' et qui renvoie le message "Greetings from Spring Boot!"
	 * @return la chaine de caractères "Greetings from Spring Boot!"
	 */
	@RequestMapping("/")
	public String index() {
		// l'instruction suivante sert seulment à tester les logs (peut étre utile plus tard)
		log.info("just clicked on home page");
		return "Greetings from Spring Boot!";
	}

	/**
	 * fonction liée a la requete http '/hello' et qui renvoie le message "Greetings from Spring Boot! 2"
	 * @return la chaine de caractères "Greetings from Spring Boot! 2"
	 */
	@RequestMapping("/hello")
	public String hello() {
		// l'instruction suivante sert seulment à tester les logs (peut étre utile plus tard)
		log.warn("just clicked on hello page");
		return "Greetings from Spring Boot! 2";
	}

	/**
	 * fonction qui génère la date en format donné en paramètre
	 * 
	 * @param pattern
	 * @return Chaine de caractères correspondant à la date d'aujourd'hui
	 */
	public static String getToday(String pattern) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(now);

	}

	/**
	 * fonction liée a la requete http '/date' et qui renvoie la date d'aujourd'hui en format AAAA/MM/JJ
	 * 
	 * @return Chaine de caractères correspondant à la date d'aujourd'hui
	 */
	@RequestMapping("/date")
	public String getDate() {

		// l'instruction suivante sert seulment à tester les logs (peut être utile plus tard)
		log.error("just clicked on date page");
		String now = getToday("YYYY/MM/DD");

		return "hello it's : " + now;
	}
}
