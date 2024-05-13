package com.jeremw.bookstore.bookservice.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a Book entity in the application.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	/**
	 * The unique identifier of the book.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * The title of the book.
	 */
	@Column(updatable = false)
	private String title;

	/**
	 * The description of the book.
	 */
	private String description;

}
