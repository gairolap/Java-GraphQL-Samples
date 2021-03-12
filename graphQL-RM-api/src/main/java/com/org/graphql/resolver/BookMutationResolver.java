/**
 * Mutation resolver for Book-Store. 
 */
package com.org.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.org.graphql.dto.BookDTO;
import com.org.graphql.model.Book;
import com.org.graphql.model.BookResponse;
import com.org.graphql.repo.BookRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookMutationResolver implements GraphQLMutationResolver {

	@Autowired
	BookRepository bookRepo;

	/**
	 * Update an existing book.
	 * 
	 * @param {@link BookDTO}.
	 * @return {@link BookResponse}.
	 */
	public BookResponse updateBook(BookDTO bookDTO) {

		bookRepo.save(this.dtoToEntity(bookDTO));
		return BookResponse.builder().responseObj("Book details updated successfully!")
				.responseStatus(HttpStatus.OK.name()).responseCode(String.valueOf(HttpStatus.OK.value())).build();
	}

	/**
	 * Create a new book.
	 * 
	 * @param {@link BookDTO}.
	 * @return {@link BookResponse}.
	 */
	public BookResponse createBook(BookDTO bookDTO) {

		bookRepo.save(this.dtoToEntity(bookDTO));
		return BookResponse.builder().responseObj("Book details created successfully!")
				.responseStatus(HttpStatus.CREATED.name()).responseCode(String.valueOf(HttpStatus.CREATED.value()))
				.build();
	}

	/**
	 * Delete a book.
	 * 
	 * @param id String.
	 * @return {@link BookResponse}.
	 */
	public BookResponse deleteBook(String id) {

		bookRepo.deleteById(id);
		return BookResponse.builder().responseObj("Book deleted successfully!")
				.responseStatus(HttpStatus.NO_CONTENT.name())
				.responseCode(String.valueOf(HttpStatus.NO_CONTENT.value())).build();
	}

	/**
	 * Converts DTO (BookDTO) to Entity (Book).
	 * 
	 * @param {@link BookDTO}.
	 * @return {@link Book}.
	 */
	private Book dtoToEntity(BookDTO bookDTO) {

		Book book = Book.builder().isin(bookDTO.getIsin()).title(bookDTO.getTitle()).publisher(bookDTO.getPublisher())
				.author(bookDTO.getAuthor()).publishedDate(bookDTO.getPublishedDate()).build();
		return book;
	}

}