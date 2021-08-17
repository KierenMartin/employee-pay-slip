
package com.kierenmartin.payslip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class.
 * Runs spring boot!
 */
@SpringBootApplication // < A convenience annotation... https://spring.io/guides/gs/spring-boot/#scratch
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}