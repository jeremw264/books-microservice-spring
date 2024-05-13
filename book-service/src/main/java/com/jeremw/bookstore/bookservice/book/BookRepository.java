package com.jeremw.bookstore.bookservice.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents the repository for managing Book entities in the database.
 * <p>
 * It extends JpaRepository to inherit basic CRUD (Create, Read, Update, Delete) operations
 * for Book entities with their primary key of type Long.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
