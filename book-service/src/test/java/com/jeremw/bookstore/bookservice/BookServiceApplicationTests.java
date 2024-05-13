package com.jeremw.bookstore.bookservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class BookServiceApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> BookServiceApplication.main(new String[] {}));
	}

}
