package com.jeremw.bookstore.bookservice.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * This class represents a Data Transfer Object (DTO) for a Book entity.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Data
public class BookDto {

	/**
	 * The unique identifier of the book.
	 */
	@Schema(description = "The unique identifier of the book")
	private Long id;

	/**
	 * The title of the book.
	 */
	@Schema(description = "The title of the book")
	private String title;

	/**
	 * The description of the book.
	 */
	@Schema(description = "The description of the book")
	private String description;
}