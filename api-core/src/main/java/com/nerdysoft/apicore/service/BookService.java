package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface BookService {

  BookReadPojo get(@Nonnull Long id);

  List<BookReadPojo> get(@Nonnull String memberName);

  List<BookReadPojo> getBorrowed(@Nonnull final String title);

  BookReadPojo create(@Nonnull BookWritePojo bookWritePojo);

  BookReadPojo update(@Nonnull Long id,
                      @Nonnull BookWritePojo bookWritePojo);

  void delete(@Nonnull Long id);
}
