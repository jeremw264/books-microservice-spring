package com.jeremw.bookstore.authservice.user;

import java.util.List;

import com.jeremw.bookstore.authservice.exception.ResourceException;
import com.jeremw.bookstore.authservice.user.dto.CreateUserForm;
import com.jeremw.bookstore.authservice.user.dto.UpdateUserForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() {
		log.info("Getting all users.");
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) throws ResourceException {
		log.info("Getting user by ID: {}", userId);
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceException("UserNotFound", "The user ID is not found in the database.", HttpStatus.NOT_FOUND));

	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceException("UserNotFound", "The user ID is not found in the database.", HttpStatus.NOT_FOUND));
	}

	@Override
	public User createUser(CreateUserForm createUserForm) throws ResourceException {

		log.info("Creating a new user with username: {}", createUserForm.getUsername());

		User userToCreate = User.builder().username(createUserForm.getUsername()).email(createUserForm.getEmail())
				.password(passwordEncoder.encode(createUserForm.getPassword()))
				.build();

		try {
			return userRepository.save(userToCreate);
		}
		catch (DataIntegrityViolationException e) {
			throw new ResourceException("UserAlreadyExists", "The user " + createUserForm.getUsername() + " already exists.", HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			throw new ResourceException("CreateUserError", "Error while creating the user " + createUserForm.getUsername() + ".", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public User updateUserById(final Long userId, final UpdateUserForm updateUserForm) throws ResourceException {
		log.info("Updating user with ID: {}", userId);

		User userDatabase = getUserById(userId);
		String newEmail = updateUserForm.getEmail();
		String newPassword = updateUserForm.getPassword();

		if (newEmail != null && !newEmail.isEmpty()) {
			userDatabase.setEmail(newEmail);
		}

		if (newPassword != null && !newPassword.isEmpty()) {
			userDatabase.setPassword(passwordEncoder.encode(newPassword));
		}

		try {
			return userRepository.save(userDatabase);
		}
		catch (Exception e) {
			throw new ResourceException("UpdateUserError",
					"Error while updating the user with the ID : " + userId.toString(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteUserById(Long userId) throws ResourceException {
		log.info("Deleting user with ID: {}", userId);
		try {
			userRepository.delete(getUserById(userId));
		}
		catch (Exception e) {
			throw new ResourceException("DeleteUserError", "Error while deleting the user with the ID : " + userId.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
