package com.jeremw.bookstore.authservice.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
