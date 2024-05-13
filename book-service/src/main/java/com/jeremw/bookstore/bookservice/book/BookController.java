package com.jeremw.bookstore.bookservice.book;

import java.util.List;

import com.jeremw.bookstore.bookservice.book.dto.BookDto;
import com.jeremw.bookstore.bookservice.book.dto.CreateBookForm;
import com.jeremw.bookstore.bookservice.book.dto.UpdateBookForm;
import com.jeremw.bookstore.bookservice.exception.ResourceException;
import com.jeremw.bookstore.bookservice.exception.ResourceExceptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This interface defines REST endpoints for book management.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Tag(name = "Books Endpoint")
@RequestMapping("/books")
@RestController
public interface BookController {

	/**
	 * Retrieves all books.
	 *
	 * @return A ResponseEntity containing a list of books.
	 */
	@Operation(summary = "Get all books", description = "Returns a list of all books.")
	@ApiResponse(responseCode = "200", description = "Success")
	@GetMapping
	ResponseEntity<List<BookDto>> getBooks() throws ResourceException;

	/**
	 * Retrieve a book by its ID.
	 *
	 * @param bookId the ID of the book
	 * @return ResponseEntity containing the BookDto object
	 */
	@Operation(summary = "Get book by ID", description = "Returns an book based on the provided ID.")
	@ApiResponse(responseCode = "200", description = "Success")
	@ApiResponse(responseCode = "404", description = "Book not found", content = @Content(schema = @Schema(implementation = ResourceExceptionDto.class)))
	@GetMapping("/{bookId}")
	ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) throws ResourceException;

	/**
	 * Create a new book.
	 *
	 * @param createBookForm the form data for creating the book
	 * @return ResponseEntity containing the created BookDto object
	 */
	@Operation(summary = "Create a new book", description = "Creates a new book.")
	@ApiResponse(responseCode = "201", description = "Book created successfully")
	@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ResourceExceptionDto.class)))
	@PostMapping
	ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookForm createBookForm) throws ResourceException;

	/**
	 * Update a book by its ID.
	 *
	 * @param bookId         the ID of the book to update
	 * @param updateBookForm the form data for updating the book
	 * @return ResponseEntity containing the updated BookDto object
	 */
	@Operation(summary = "Update an existing book",
			description = "Updates an existing book based on the provided ID.")
	@ApiResponse(responseCode = "200", description = "Book updated successfully")
	@ApiResponse(responseCode = "404", description = "Book not found", content = @Content(schema = @Schema(implementation = ResourceExceptionDto.class)))
	@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ResourceExceptionDto.class)))
	@PatchMapping("/{bookId}")
	ResponseEntity<BookDto> updateBookById(@PathVariable Long bookId, @Valid @RequestBody UpdateBookForm updateBookForm) throws ResourceException;

	/**
	 * Delete a book by its ID.
	 *
	 * @param bookId the ID of the book to delete
	 * @return ResponseEntity indicating the result of the deletion
	 */
	@Operation(summary = "Delete an book by ID", description = "Deletes an book based on the provided ID.")
	@ApiResponse(responseCode = "204", description = "Book deleted successfully")
	@ApiResponse(responseCode = "404", description = "Book not found", content = @Content(schema = @Schema(implementation = ResourceExceptionDto.class)))
	@DeleteMapping("/{bookId}")
	ResponseEntity<Void> deleteBookById(@PathVariable Long bookId) throws ResourceException;

}