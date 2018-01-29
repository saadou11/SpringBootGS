package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import main.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import repositories.CustomerRepository;
import entities.Customer;
import entities.User;

@RestController
public class HelloController {

	HashMap<Long, User> userList = new HashMap<Long, User>();

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	CustomerRepository custumRepository;

	/**
	 * fonction liée a la requete http '/' et qui renvoie le message "Greetings from Spring Boot!"
	 * 
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
	 * 
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

	/**
	 * fonction qui simule la requetes http POST en créant un objet de type User dont les attribus sont passé en paramétre
	 * 
	 * @param name
	 * @param age
	 * @return user 
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User postUser(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
		long tmp = counter.incrementAndGet();
		User u = new User(tmp, name, age);
		userList.put(tmp, u);
		return u;
	}

	/**
	 * fonction qui simule la requetes http GET en retournant un objet de type User
	 * 
	 * @param id
	 * @return user 
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser(@RequestParam(value = "id") long id) {
		if (userList.containsKey(id)) {
			return userList.get(id);
		}
		return null;
	}

	/**
	 * fonction qui simule la requetes http PUT en modifiant un objet de type user dont les attribus sont passé en paramétre
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @return User
	 */
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public User putUser(@RequestParam(value = "id") long id, @RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
		userList.get(id).setName(name);
		userList.get(id).setAge(age);
		return userList.get(id);
	}

	/**
	 * fonction qui simule la requetes http PUT en supprimant un objet de type user dont les attribus sont passé en paramétre
	 * @param id
	 * @return userList
	 */
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public HashMap<Long, User> deleteUser(@RequestParam(value = "id") long id) {
		userList.remove(id);
		return userList;
	}
	
	@RequestMapping(value="/CustomerId")
	public Customer findCustomer(@RequestParam(value="id") String id){
		
		Customer c = custumRepository.findOne(Long.valueOf(id));
		log.info("FROM CONTROLLER :"+c.toString());
		
		return c;
	}
	@RequestMapping(value="/ListCustomerId")
	public List<Customer> findListCustomerByLastName(@RequestParam(value="LastName") String lastName){
		return custumRepository.findByLastName(lastName);
	}
	
	@RequestMapping(value = "/AddCustomer", method = RequestMethod.POST)
	public List<Customer> postCustomer(@RequestParam(value = "LastName") String lastName, @RequestParam(value = "FirstName") String FirstName) {
		custumRepository.save(new Customer(lastName,FirstName));
		return custumRepository.findAll();
	}

}
