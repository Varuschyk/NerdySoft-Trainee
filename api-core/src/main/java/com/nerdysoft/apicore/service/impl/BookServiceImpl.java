package com.nerdysoft.apicore.service.impl;

import java.util.List;
import java.util.Objects;

import com.nerdysoft.apicore.exception.book.BookBadRequestException;
import com.nerdysoft.apicore.exception.book.BookNotFoundException;
import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.persistence.repository.BookJPARepository;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import com.nerdysoft.apicore.service.BookService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional(readOnly = true)
  public List<BookReadPojo> getBorrowedByMember(@Nonnull final String memberName) {
    final var books =  bookJPARepository.findAllByMembersIsNotEmptyAndMembersName(memberName);
    return books.stream().map(bookMapper::toBookReadPojo).toList();
  }

  @Nonnull
  @Override
  @Transactional(readOnly = true)
  public List<BookReadPojo> getBorrowedByTitle(@Nonnull final String title) {
    final var books =  bookJPARepository.findAllByMembersIsNotEmptyAndTitle(title);
    return books.stream().map(bookMapper::toBookReadPojo).toList();
  }

  @Nonnull
  @Override
  @Transactional
  public BookReadPojo create(@Nonnull final BookWritePojo bookWritePojo) {
    final var bookToSave = bookMapper.toBookEntity(bookWritePojo);
    final var savedBookEntity = bookJPARepository.upsertBook(bookToSave.getAuthor(), bookToSave.getTitle());
    return bookMapper.toBookReadPojo(savedBookEntity);
  }

  @Nonnull
  @Override
  @Transactional
  public BookReadPojo update(@Nonnull final Long id,
                             @Nonnull final BookWritePojo bookWritePojo) {
    final var updateBookEntity = bookJPARepository.updateBookById(id,
        bookWritePojo.getTitle(), bookWritePojo.getAuthor(), bookWritePojo.getAmount())
        .orElseThrow(() -> new BookNotFoundException("Book not found"));
    return bookMapper.toBookReadPojo(updateBookEntity);
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
