package com.jeremw.bookstore.authservice.user;

import java.util.List;

import com.jeremw.bookstore.authservice.user.dto.CreateUserForm;
import com.jeremw.bookstore.authservice.user.dto.UpdateUserForm;
import com.jeremw.bookstore.authservice.exception.ResourceException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Service
public interface UserService extends UserDetailsService {

	/**
	 * Retrieves a list of all users.
	 *
	 * @return List of User objects representing all users.
	 */
	List<User> getAllUsers();

	/**
	 * Retrieves a user by their unique identifier.
	 *
	 * @param userId The unique identifier of the user.
	 * @return User object representing the specified user.
	 * @throws ResourceException if the user with the given ID is not found.
	 */
	User getUserById(Long userId) throws ResourceException;

	/**
	 * Creates a new user based on the provided form.
	 *
	 * @param createUserForm The form containing information to create a new user.
	 * @return User object representing the newly created user.
	 * @throws ResourceException if an error occurs while creating the user.
	 */
	User createUser(CreateUserForm createUserForm) throws ResourceException;

	/**
	 * Updates an existing user based on the provided form.
	 *
	 * @param userId         The unique identifier of user.
	 * @param updateUserForm The form containing information to update an existing user.
	 * @return User object representing the updated user.
	 * @throws ResourceException if an error occurs while updating the user.
	 */
	User updateUserById(Long userId, UpdateUserForm updateUserForm) throws ResourceException;

	/**
	 * Deletes a user by their unique identifier.
	 *
	 * @param userId The unique identifier of the user to be deleted.
	 * @throws ResourceException if an error occurs while deleting the user.
	 */
	void deleteUserById(Long userId) throws ResourceException;

}
