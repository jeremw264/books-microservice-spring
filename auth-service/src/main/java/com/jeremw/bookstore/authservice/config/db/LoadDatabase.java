package com.jeremw.bookstore.authservice.config.db;

import com.jeremw.bookstore.authservice.user.UserService;
import com.jeremw.bookstore.authservice.user.dto.CreateUserForm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to initialize the database with default data.
 *
 * <p>
 * This class defines a CommandLineRunner bean that is executed when the application
 * context is initialized. It creates default users in the database to be used
 * during application runtime.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Slf4j
@Configuration
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(UserService userService) {
		return args -> {
			log.info("Init Database");

			CreateUserForm createUserForm = CreateUserForm.builder()
					.username("root")
					.email("root@bookstore.com")
					.password("toor")
					.build();

			userService.createUser(createUserForm);
		};
	}

}