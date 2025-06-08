package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import jakarta.annotation.Nonnull;

public interface BookService {

  BookReadPojo get(@Nonnull Long id);

  BookReadPojo create(@Nonnull BookWritePojo bookWritePojo);

  BookReadPojo update(@Nonnull Long id,
                      @Nonnull BookWritePojo bookWritePojo);

  BookReadPojo delete(@Nonnull Long id);
}
