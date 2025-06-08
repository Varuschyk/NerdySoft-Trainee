package com.nerdysoft.apicore.service.impl;

import com.nerdysoft.apicore.exception.book.BookBadRequestException;
import com.nerdysoft.apicore.exception.book.BookNotFoundException;
import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.persistence.entity.BookEntity;
import com.nerdysoft.apicore.persistence.repository.BookJPARepository;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import com.nerdysoft.apicore.service.BookService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookJPARepository bookJPARepository;
  private final BookMapper bookMapper;

  @Nonnull
  @Override
  @Transactional(readOnly = true)
  public BookReadPojo get(@Nonnull final Long id) {
    final var bookEntity = bookJPARepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));
    return bookMapper.toBookReadPojo(bookEntity);
  }

  @Nonnull
  @Override
  @Transactional
  public BookReadPojo create(@Nonnull final BookWritePojo bookWritePojo) {
    BookEntity bookToSave = bookMapper.toBookEntity(bookWritePojo);

    final var bookEntity = bookJPARepository.findByTitleAndAuthor(bookToSave.getTitle(), bookToSave.getAuthor());
    if (bookEntity.isPresent()) {
      bookToSave = bookEntity.get();
      bookToSave.setAmount(bookToSave.getAmount() + 1);
    }

    final var savedBookEntity = bookJPARepository.save(bookToSave);
    return bookMapper.toBookReadPojo(savedBookEntity);
  }

  @Nonnull
  @Override
  @Transactional
  public BookReadPojo update(@Nonnull final Long id,
                             @Nonnull final BookWritePojo bookWritePojo) {
    final var bookEntity = bookJPARepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));
    final var bookEntityToUpdate = bookMapper.toBookEntity(bookWritePojo);
    bookEntityToUpdate.setId(bookEntity.getId());
    final var updatedBookEntity = bookJPARepository.save(bookEntityToUpdate);
    return bookMapper.toBookReadPojo(updatedBookEntity);
  }

  @Nonnull
  @Override
  @Transactional
  public BookReadPojo delete(@Nonnull final Long id) {
    final var bookEntity = bookJPARepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));

    if (Objects.nonNull(bookEntity.getMembers()) && !bookEntity.getMembers().isEmpty()) {
      throw new BookBadRequestException("A book can not be deleted if at least one of it is borrowed.");
    }

    bookJPARepository.delete(bookEntity);
    return bookMapper.toBookReadPojo(bookEntity);
  }
}
