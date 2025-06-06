package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface BookService {

  BookReadPojo get(@Nonnull Long id);

  List<BookReadPojo> get(@Nonnull String memberName);

  List<BookReadPojo> getBorrowedByTitle(@Nonnull String title);

  BookReadPojo create(@Nonnull BookWritePojo bookWritePojo);

  BookReadPojo update(@Nonnull Long id,
                      @Nonnull BookWritePojo bookWritePojo);

  BookReadPojo delete(@Nonnull Long id);
}
