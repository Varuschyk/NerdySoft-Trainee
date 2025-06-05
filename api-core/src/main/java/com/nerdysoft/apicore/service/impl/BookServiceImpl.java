package com.nerdysoft.apicore.service.impl;

import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.persistence.repository.BookJPARepository;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import com.nerdysoft.apicore.service.BookService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        .orElseThrow(() -> new RuntimeException("Book not found"));
    return bookMapper.toBookReadPojo(bookEntity);
  }

  @Nonnull
  @Override
  @Transactional(readOnly = true)
  public List<BookReadPojo> get(@Nonnull final String memberName) {
    final var books = bookJPARepository.findByMembersName(memberName);
    return books.stream().map(bookMapper::toBookReadPojo).toList();
  }

  @Override
  public List<BookReadPojo> getBorrowed(@Nonnull String title) {
    final var books = bookJPARepository.findByMembersIsNotEmpty(title);
    return books.stream().map(bookMapper::toBookReadPojo).toList();
  }

  @Nonnull
  @Override
  @Transactional
  public BookReadPojo create(@Nonnull final BookWritePojo bookWritePojo) {
    final var bookToSave = bookMapper.toBookEntity(bookWritePojo);
    final var savedBookEntity = bookJPARepository.save(bookToSave);
    return bookMapper.toBookReadPojo(savedBookEntity);
  }

  @Nonnull
  @Override
  @Transactional
  public BookReadPojo update(@Nonnull final Long id,
                             @Nonnull final BookWritePojo bookWritePojo) {
    final var bookEntity = bookJPARepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found"));
    bookEntity.setTitle(bookWritePojo.getTitle());
    bookEntity.setAuthor(bookWritePojo.getAuthor());
    bookEntity.setAmount(bookWritePojo.getAmount());
    final var updateBookEntity = bookJPARepository.save(bookEntity);
    return bookMapper.toBookReadPojo(updateBookEntity);
  }

  @Override
  @Transactional
  public void delete(@Nonnull final Long id) {
    bookJPARepository.findById(id)
        .ifPresentOrElse(bookJPARepository::delete, () -> new RuntimeException("Book not found"));
  }
}
