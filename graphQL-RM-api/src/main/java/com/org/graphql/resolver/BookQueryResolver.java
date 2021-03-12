/**
 * Query resolver for Book-Store.
 */
package com.org.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.org.graphql.model.BookResponse;
import com.org.graphql.repo.BookRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookQueryResolver implements GraphQLQueryResolver {

	@Autowired
	BookRepository bookRepo;

	/**
	 * Get all the books.
	 * 
	 * @return {@link BookResponse}.
	 */
	public BookResponse allBooks() {

		log.info("Getting all Books...");
		return BookResponse.builder().responseObj(bookRepo.findAll()).responseStatus(HttpStatus.OK.name())
				.responseCode(String.valueOf(HttpStatus.OK.value())).build();
	}

	/**
	 * Get the book for the given isin.
	 * 
	 * @param id String.
	 * @return {@link BookResponse}.
	 */
	public BookResponse book(String id) {

		log.info("Getting book with isin {}", id);
		return (bookRepo.findById(id).isPresent())
				? BookResponse.builder().responseObj(bookRepo.findById(id).get()).responseStatus(HttpStatus.OK.name())
						.responseCode(String.valueOf(HttpStatus.OK.value())).build()
						: BookResponse.builder().responseObj("No book found associated with the given isin..." + id)
						.responseStatus(HttpStatus.NOT_FOUND.name())
						.responseCode(String.valueOf(HttpStatus.NOT_FOUND.value())).build();
	}

}