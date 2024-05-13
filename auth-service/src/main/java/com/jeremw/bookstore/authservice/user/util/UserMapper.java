package com.jeremw.bookstore.authservice.user.util;

import java.util.List;

import com.jeremw.bookstore.authservice.user.User;
import com.jeremw.bookstore.authservice.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting {@link User} entities to {@link UserDto} DTOs
 * and vice versa.
 *
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 13/05/2024
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

	/**
	 * Singleton instance of the UserMapper interface.
	 */
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDto toDto(User entity);

	List<UserDto> toDtoList(List<User> entityList);

}
