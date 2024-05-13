package com.jeremw.bookstore.bookservice.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jeremw.bookstore.bookservice.book.dto.CreateBookForm;
import com.jeremw.bookstore.bookservice.book.dto.UpdateBookForm;
import com.jeremw.bookstore.bookservice.exception.ResourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@ExtendWith(SpringExtension.class)
class BookServiceImplTests {

	@Mock
	private BookRepository bookRepository;


	@InjectMocks
	private BookServiceImpl bookService;


	@BeforeEach
	void setup() {
	}

	@Test
	void testGetBooks() {
		List<Book> books = new ArrayList<>();
		when(bookRepository.findAll()).thenReturn(books);

		List<Book> result = bookService.getBooks();

		assertNotNull(result);
		assertEquals(books, result);
		verify(bookRepository, times(1)).findAll();
	}

	@Test
	void testGetBookById() throws ResourceException {
		Long bookId = 1L;

		Book book = Book.builder().id(bookId).build();
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

		Book result = bookService.getBookById(bookId);

		verify(bookRepository, times(1)).findById(bookId);
		assertNotNull(result);
		assertEquals(book, result);
	}

	@Test
	void testGetBookByIdNotFound() {
		Long bookId = 1L;

		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

		assertThrows(ResourceException.class, () -> bookService.getBookById(bookId));

		verify(bookRepository, times(1)).findById(bookId);
	}

	@Test
	void testCreateBook() throws ResourceException {
		CreateBookForm createBookForm = CreateBookForm.builder()
				.title("newBook")
				.description("newDescription")
				.build();

		Book bookToCreate = Book.builder()
				.title(createBookForm.getTitle())
				.description(createBookForm.getDescription())
				.build();

		when(bookRepository.save(bookToCreate)).thenReturn(bookToCreate);

		Book result = bookService.createBook(createBookForm);

		assertNotNull(result);
		assertEquals(bookToCreate, result);
		verify(bookRepository, times(1)).save(bookToCreate);
	}

	@Test
	void testCreateBookError() {
		CreateBookForm createBookForm = CreateBookForm.builder()
				.title("newBook")
				.description("newDescription")
				.build();

		Book bookToCreate = Book.builder()
				.title(createBookForm.getTitle())
				.description(createBookForm.getDescription())
				.build();

		when(bookRepository.save(bookToCreate)).thenThrow(RuntimeException.class);

		assertThrows(ResourceException.class, () -> bookService.createBook(createBookForm));
		verify(bookRepository, times(1)).save(bookToCreate);
	}

	@Test
	void testUpdateBook() throws ResourceException {
		UpdateBookForm updateBookForm = UpdateBookForm.builder()
				.description("newDescription")
				.build();


		Book existingBook = Book.builder()
				.id(1L)
				.title("book1")
				.description("newDescription")
				.build();

		when(bookRepository.findById(existingBook.getId())).thenReturn(Optional.of(existingBook));
		when(bookRepository.save(existingBook)).thenReturn(existingBook);

		Book result = bookService.updateBookById(existingBook.getId(), updateBookForm);

		assertNotNull(result);
		assertEquals(existingBook, result);
		assertEquals(updateBookForm.getDescription(), existingBook.getDescription());
		verify(bookRepository, times(1)).findById(existingBook.getId());
		verify(bookRepository, times(1)).save(existingBook);
	}

	@Test
	void testUpdateBookNotFound() {
		Long bookId = 1L;

		UpdateBookForm updateBookForm = UpdateBookForm.builder().build();

		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

		assertThrows(ResourceException.class, () -> bookService.updateBookById(bookId, updateBookForm));
		verify(bookRepository, times(1)).findById(bookId);
	}

	@Test
	void testUpdateBookError() {
		UpdateBookForm updateBookForm = UpdateBookForm.builder().build();

		Book existingBook = Book.builder()
				.id(1L)
				.title("book1")
				.description("description")
				.build();

		when(bookRepository.findById(existingBook.getId())).thenReturn(Optional.of(existingBook));
		when(bookRepository.save(existingBook)).thenThrow(RuntimeException.class);

		assertThrows(ResourceException.class, () -> bookService.updateBookById(existingBook.getId(), updateBookForm));
	}

	@Test
	void testDeleteBook() throws ResourceException {
		Long bookId = 1L;

		Book existingBook = Book.builder()
				.id(1L)
				.title("book1")
				.description("description")
				.build();

		when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));

		bookService.deleteBookById(bookId);

		verify(bookRepository, times(1)).findById(bookId);
		verify(bookRepository, times(1)).delete(existingBook);
	}

	@Test
	void testDeleteBookNotFound() {
		Long bookId = 1L;

		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

		assertThrows(ResourceException.class, () -> bookService.deleteBookById(bookId));

		verify(bookRepository, times(1)).findById(bookId);
	}

	@Test
	void testDeleteBookError() {
		Long bookId = 1L;

		Book existingBook = Book.builder()
				.id(bookId)
				.title("book1")
				.description("description")
				.build();

		when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
		doThrow(RuntimeException.class).when(bookRepository).delete(existingBook);

		assertThrows(ResourceException.class, () -> bookService.deleteBookById(bookId));
		verify(bookRepository, times(1)).delete(existingBook);
		verify(bookRepository, times(1)).findById(bookId);
	}

}