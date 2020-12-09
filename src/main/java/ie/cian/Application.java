package ie.cian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * I am pretty sure this overall class is what handles the running of everything.
 * So it goes into applicaiton.properties and sees which application to run when thsi is run
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
