package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import jakarta.annotation.Nonnull;

/**
 * Abstraction over BookEntity services.
 */
public interface BookService {

  /**
   * Retrieves the books by the given id.
   *
   * @param id the given book id.
   * @return the book by the given id.
   */
  BookReadPojo get(@Nonnull Long id);

  /**
   * Creates a new book with the given data.
   *
   * @param bookWritePojo the book to create.
   * @return the created book.
   */
  BookReadPojo create(@Nonnull BookWritePojo bookWritePojo);

  /**
   * Updates the book by the given id with the given data.
   *
   * @param id the id of the book to update.
   * @param bookWritePojo the book data to update.
   * @return the updated book.
   */
  BookReadPojo update(@Nonnull Long id,
                      @Nonnull BookWritePojo bookWritePojo);

  /**
   * Deletes the book by the given static id.
   *
   * @param id the id of the book to delete.
   * @return the deleted book.
   */
  BookReadPojo delete(@Nonnull Long id);
}
