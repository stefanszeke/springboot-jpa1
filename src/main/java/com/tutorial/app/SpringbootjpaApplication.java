package com.tutorial.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.tutorial.controllers", "com.tutorial.services"}) // ComponentScan means that Spring will scan these packages for @Component annotations
@EntityScan("com.tutorial.entities")	// EntityScan means that Spring will scan these packages for @Entity annotations
@EnableJpaRepositories("com.tutorial.repositories")	// EnableJpaRepositories means that Spring will scan these packages for @Repository annotations

public class SpringbootjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);

		System.out.println();
		System.out.println("Server started!");
	}

}
