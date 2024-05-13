package com.jeremw.bookstore.bookservice.book;

import java.net.URI;
import java.util.List;

import com.jeremw.bookstore.bookservice.book.dto.BookDto;
import com.jeremw.bookstore.bookservice.book.dto.CreateBookForm;
import com.jeremw.bookstore.bookservice.book.dto.UpdateBookForm;
import com.jeremw.bookstore.bookservice.book.util.BookMapper;
import com.jeremw.bookstore.bookservice.exception.ResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Implementation of the {@link BookController} interface for managing books.
 *
 * <p>
 * This class handles HTTP requests related to book operations, such as fetching,
 * creating, updating, and deleting books.
 * </p>
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

	private final BookService bookService;

	/**
	 * Retrieves all books.
	 *
	 * @return A ResponseEntity containing a list of books.
	 */
	@Override
	public ResponseEntity<List<BookDto>> getBooks() throws ResourceException {
		log.debug("Getting all books.");
		List<BookDto> bookDtoList = BookMapper.INSTANCE.toDtoList(bookService.getBooks());
		return ResponseEntity.status(HttpStatus.OK).body(bookDtoList);
	}

	/**
	 * Retrieve a book by its ID.
	 *
	 * @param bookId the ID of the book
	 * @return ResponseEntity containing the BookDto object
	 */
	@Override
	public ResponseEntity<BookDto> getBookById(final Long bookId) throws ResourceException {
		log.debug("Getting book by ID: {}", bookId);
		BookDto bookDto = BookMapper.INSTANCE.toDto(bookService.getBookById(bookId));
		return ResponseEntity.status(HttpStatus.OK).body(bookDto);
	}

	/**
	 * Create a new book.
	 *
	 * @param createBookForm the form data for creating the book
	 * @return ResponseEntity containing the created BookDto object
	 */
	@Override
	public ResponseEntity<BookDto> createBook(final CreateBookForm createBookForm) throws ResourceException {
		log.debug("Creating a new book with title: {}", createBookForm.getTitle());

		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/books").toUriString());
		BookDto createdBookDto = BookMapper.INSTANCE.toDto(bookService.createBook(createBookForm));

		log.debug("Book created successfully with title: {}", createBookForm.getTitle());
		return ResponseEntity.created(uri).body(createdBookDto);
	}

	/**
	 * Update a book by its ID.
	 *
	 * @param bookId         the ID of the book to update
	 * @param updateBookForm the form data for updating the book
	 * @return ResponseEntity containing the updated BookDto object
	 */
	@Override
	public ResponseEntity<BookDto> updateBookById(final Long bookId, final UpdateBookForm updateBookForm) throws ResourceException {
		log.debug("Updating book with ID: {}", bookId);

		BookDto updatedBookDto = BookMapper.INSTANCE.toDto(bookService.updateBookById(bookId, updateBookForm));

		log.debug("Book updated successfully with ID: {}", bookId);
		return ResponseEntity.status(HttpStatus.OK).body(updatedBookDto);
	}

	/**
	 * Delete a book by its ID.
	 *
	 * @param bookId the ID of the book to delete
	 * @return ResponseEntity indicating the result of the deletion
	 */
	@Override
	public ResponseEntity<Void> deleteBookById(final Long bookId) throws ResourceException {
		log.debug("Deleting book with ID: {}", bookId);

		bookService.deleteBookById(bookId);

		log.debug("Book deleted successfully with ID: {}", bookId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}

