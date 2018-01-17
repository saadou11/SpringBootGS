package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe principale qui charge toutes les composantes et configuration et qui execute l'application SpringBoot 
 * @author Saadou
 * 
 */
@SpringBootApplication
@ComponentScan(basePackages = { "controllers"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
