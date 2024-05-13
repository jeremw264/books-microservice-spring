package com.jeremw.bookstore.bookservice.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a form used for creating a new book.
 * <p>
 * It contains fields for the title and description of the new book.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookForm {

	/**
	 * The title of the new book.
	 */
	@NotBlank(message = "The title is required to create a new book.")
	@Schema(description = "The title of the new book")
	private String title;

	/**
	 * The description of the new book.
	 */
	@NotBlank(message = "The description is required to create a new book.")
	@Schema(description = "The description of the new book")
	private String description;
}
