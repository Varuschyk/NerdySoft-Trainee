package com.nerdysoft.apicore.service.impl;

import java.util.List;

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
        .orElseThrow(() -> new RuntimeException("Book not found"));
    return bookMapper.toBookReadPojo(bookEntity);
  }

  @Nonnull
  @Override
  @Transactional(readOnly = true)
  public List<BookReadPojo> get(@Nonnull final String memberName) {
    final var books =  bookJPARepository.findAllByMembersIsNotEmptyAndTitle(memberName);
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
    final var alreadyExistedBookEntity =
        bookJPARepository.findByAuthorAndTitle(bookToSave.getAuthor(), bookToSave.getTitle());

    if (alreadyExistedBookEntity.isPresent()) {
      final var updateBookEntity = alreadyExistedBookEntity.get();
      updateBookEntity.setAmount(updateBookEntity.getAmount() + 1);
      final var updatedBookEntity = bookJPARepository.save(updateBookEntity);
      return bookMapper.toBookReadPojo(updatedBookEntity);
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
        .orElseThrow(() -> new RuntimeException("Book not found"));
    bookEntity.setTitle(bookWritePojo.getTitle());
    bookEntity.setAuthor(bookWritePojo.getAuthor());
    bookEntity.setAmount(bookWritePojo.getAmount());
    final var updateBookEntity = bookJPARepository.save(bookEntity);
    return bookMapper.toBookReadPojo(updateBookEntity);
  }

  @Override
  @Transactional
  public BookReadPojo delete(@Nonnull final Long id) {
    final var bookEntity = bookJPARepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found"));

    if (bookJPARepository.existsByMembersIsNotEmptyAndId(id)) {
      throw new RuntimeException("A book can not be deleted if at least one of it is borrowed.");
    }

    bookJPARepository.deleteById(id);
    return bookMapper.toBookReadPojo(bookEntity);
  }
}
