package com.jeremw.bookstore.authservice.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateUserForm {

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "The email is not valid")
	private String email;

	private String password;

}