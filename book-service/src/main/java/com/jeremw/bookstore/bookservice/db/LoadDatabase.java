package com.jeremw.bookstore.bookservice.db;

import com.jeremw.bookstore.bookservice.book.BookService;
import com.jeremw.bookstore.bookservice.book.dto.CreateBookForm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Slf4j
@Configuration
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(BookService bookService) {
		return args -> {
			log.info("Init Database");

			CreateBookForm createBookForm = CreateBookForm.builder()
					.title("book-title")
					.description("book-description")
					.build();

			bookService.createBook(createBookForm);
		};
	}
}
