package com.jeremw.bookstore.bookservice.book;

import java.util.List;

import com.jeremw.bookstore.bookservice.book.dto.CreateBookForm;
import com.jeremw.bookstore.bookservice.book.dto.UpdateBookForm;
import com.jeremw.bookstore.bookservice.exception.ResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	/**
	 * Retrieves all books.
	 *
	 * @return a list of Book objects
	 */
	@Override
	public List<Book> getBooks() {
		log.debug("Fetching all books.");
		return bookRepository.findAll();
	}

	/**
	 * Retrieves a book by its ID.
	 *
	 * @param bookId the ID of the book to retrieve
	 * @return the Book object with the specified ID
	 * @throws ResourceException if there is an issue retrieving the book
	 */
	@Override
	public Book getBookById(final Long bookId) throws ResourceException {
		log.debug("Fetching book by ID: {}", bookId);
		return bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceException("BookNotFound", "The book ID is not found in the database.",
						HttpStatus.NOT_FOUND));
	}

	/**
	 * Creates a new book based on the provided form data.
	 *
	 * @param createBookForm the form data for creating the book
	 * @return the created Book object
	 * @throws ResourceException if there is an issue creating the book
	 */
	@Override
	public Book createBook(final CreateBookForm createBookForm) throws ResourceException {
		log.debug("Creating book: {}", createBookForm.getTitle());
		Book bookToCreate = Book.builder()
				.title(createBookForm.getTitle())
				.description(createBookForm.getDescription())
				.build();

		try {
			Book createdBook = bookRepository.save(bookToCreate);
			log.info("Book created successfully: {}", createdBook.getId());
			return createdBook;
		}
		catch (Exception e) {
			log.error("Error creating book: {}", e.getMessage());
			throw new ResourceException("CreateBookError", "Error while creating the book '"
					+ createBookForm.getTitle() + ".",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Updates a book by its ID.
	 *
	 * @param bookId         the ID of the book to update
	 * @param updateBookForm the form data for updating the book
	 * @return the updated Book object
	 * @throws ResourceException if there is an issue updating the book
	 */
	@Override
	public Book updateBookById(final Long bookId, final UpdateBookForm updateBookForm) throws ResourceException {
		log.info("Updating book with ID: {}", bookId);

		Book bookDatabase = getBookById(bookId);

		String description = updateBookForm.getDescription();

		if (description != null && !description.isEmpty()) {
			bookDatabase.setDescription(description);
		}


		try {
			Book updatedBook = bookRepository.save(bookDatabase);
			log.info("Book updated successfully: {}", updatedBook.getId());
			return updatedBook;
		}
		catch (Exception e) {
			log.error("Error updating book: {}", e.getMessage());
			throw new ResourceException("UpdateBookError", "Error while updating the book with the ID '"
					+ bookId + ".",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Deletes a book by its ID.
	 *
	 * @param bookId the ID of the book to delete
	 * @throws ResourceException if there is an issue deleting the book
	 */
	@Override
	public void deleteBookById(final Long bookId) throws ResourceException {
		log.info("Deleting book with ID: {}.", bookId);
		try {
			bookRepository.delete(getBookById(bookId));
			log.info("Book deleted successfully: {}", bookId);
		}
		catch (Exception e) {
			log.error("Error deleting book: {}", e.getMessage());
			throw new ResourceException("DeleteBookError",
					"Error while deleting the book with the ID '" + bookId + ".",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
