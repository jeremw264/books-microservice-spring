package com.jeremw.bookstore.bookservice.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a form used for updating an existing book.
 * <p>
 * It contains a field for the updated description of the book.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookForm {

	/**
	 * The updated description of the book.
	 */
	private String description;
}
