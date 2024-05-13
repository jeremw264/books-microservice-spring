package com.jeremw.bookstore.bookservice.book.util;

import java.util.List;

import com.jeremw.bookstore.bookservice.book.Book;
import com.jeremw.bookstore.bookservice.book.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * This interface provides mapping methods for converting between Book entities and BookDto objects.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Mapper(componentModel = "spring")
public interface BookMapper {

	/**
	 * Singleton instance of the BookMapper interface.
	 */
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	/**
	 * Converts a Book entity to a BookDto object.
	 *
	 * @param entity the Book entity to convert
	 * @return the corresponding BookDto object
	 */
	BookDto toDto(Book entity);

	/**
	 * Converts a list of Book entities to a list of BookDto objects.
	 *
	 * @param entityList the list of Book entities to convert
	 * @return the corresponding list of BookDto objects
	 */
	List<BookDto> toDtoList(List<Book> entityList);

}