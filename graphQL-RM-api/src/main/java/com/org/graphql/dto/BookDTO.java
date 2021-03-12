/**
 * DTO for Book entity.
 */
package com.org.graphql.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {

	String isin;
	String title;
	String publisher;
	String author;
	String publishedDate;

}