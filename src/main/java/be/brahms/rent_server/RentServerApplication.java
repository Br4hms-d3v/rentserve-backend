package be.brahms.rent_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Rent Server backend.
 */
@SpringBootApplication
public class RentServerApplication {

	/**
	 * Default constructor for the application.
	 */
	public RentServerApplication() {
		// No-op
	}

	/**
	 * Entry point for the Spring Boot application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(RentServerApplication.class, args);
	}

}
