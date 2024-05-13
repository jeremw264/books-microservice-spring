package com.jeremw.bookstore.bookservice.book;

import java.util.List;

import com.jeremw.bookstore.bookservice.book.dto.CreateBookForm;
import com.jeremw.bookstore.bookservice.book.dto.UpdateBookForm;
import com.jeremw.bookstore.bookservice.exception.ResourceException;

import org.springframework.stereotype.Service;


/**
 * This interface defines operations related to book management.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Service
public interface BookService {

	/**
	 * Retrieves all books.
	 *
	 * @return a list of Book objects
	 */
	List<Book> getBooks();

	/**
	 * Retrieves a book by its ID.
	 *
	 * @param bookId the ID of the book to retrieve
	 * @return the Book object with the specified ID
	 * @throws ResourceException if there is an issue retrieving the book
	 */
	Book getBookById(Long bookId) throws ResourceException;

	/**
	 * Creates a new book based on the provided form data.
	 *
	 * @param createBookForm the form data for creating the book
	 * @return the created Book object
	 * @throws ResourceException if there is an issue creating the book
	 */
	Book createBook(CreateBookForm createBookForm) throws ResourceException;

	/**
	 * Updates a book by its ID.
	 *
	 * @param bookId         the ID of the book to update
	 * @param updateBookForm the form data for updating the book
	 * @return the updated Book object
	 * @throws ResourceException if there is an issue updating the book
	 */
	Book updateBookById(Long bookId, UpdateBookForm updateBookForm) throws ResourceException;

	/**
	 * Deletes a book by its ID.
	 *
	 * @param bookId the ID of the book to delete
	 * @throws ResourceException if there is an issue deleting the book
	 */
	void deleteBookById(Long bookId) throws ResourceException;
}