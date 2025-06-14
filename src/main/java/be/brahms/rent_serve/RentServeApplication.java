package be.brahms.rent_serve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Rent Server backend.
 */
@SpringBootApplication
public class RentServeApplication {

	/**
	 * Default constructor for the application.
	 */
	public RentServeApplication() {
		// No-op
	}

	/**
	 * Entry point for the Spring Boot application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(RentServeApplication.class, args);
	}

}
