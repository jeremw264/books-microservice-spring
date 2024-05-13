package com.jeremw.bookstore.authservice.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class representing a simplified view of a user.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Data
@Builder
public class UserDto {

	/**
	 * The id of the user.
	 */
	private Long id;

	/**
	 * The username of the user.
	 */
	private String username;

	/**
	 * The email address of the user.
	 */
	private String email;

}
