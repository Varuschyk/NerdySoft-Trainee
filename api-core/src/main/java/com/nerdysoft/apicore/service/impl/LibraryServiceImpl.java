package com.nerdysoft.apicore.service.impl;

import com.nerdysoft.apicore.exception.book.BookNotFoundException;
import com.nerdysoft.apicore.exception.library.LibraryBadRequestException;
import com.nerdysoft.apicore.exception.member.MemberNotFoundException;
import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.persistence.entity.BookEntity;
import com.nerdysoft.apicore.persistence.repository.BookJPARepository;
import com.nerdysoft.apicore.persistence.repository.MemberJPARepository;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.properties.LibraryBookPropertiesHolder;
import com.nerdysoft.apicore.service.LibraryService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(LibraryBookPropertiesHolder.class)
public class LibraryServiceImpl implements LibraryService {

  private final LibraryBookPropertiesHolder libraryBookPropertiesHolder;
  private final BookJPARepository bookJPARepository;
  private final MemberJPARepository memberRepository;
  private final MemberMapper memberMapper;
  private final BookMapper bookMapper;

  @Nonnull
  @Override
  @Transactional(readOnly = true)
  public List<BookReadPojo> getBorrowedBooksByMember(@Nonnull final String memberName) {
    final var memberEntity = memberRepository.findByName(memberName)
        .orElseThrow(() -> new MemberNotFoundException(memberName));
    final var books = memberEntity.getBorrowedBooks();
    return books.stream().map(bookMapper::toBookReadPojo).toList();
  }

  @Nonnull
  @Override
  @Transactional(readOnly = true)
  public List<BookReadPojo> getBorrowedBooksByTitle(@Nonnull final String title) {
    final var memberEntity = memberRepository.findByBorrowedBooksIsNotEmptyAndBorrowedBooksTitle(title);
    final var books = memberEntity.stream().flatMap(member -> member.getBorrowedBooks().stream()).toList();
    return books.stream().map(bookMapper::toBookReadPojo).toList();
  }

  @Override
  @Transactional
  public MemberReadPojo borrowBook(@Nonnull final Long memberId,
                                   @Nonnull final Long bookId) {
    final var memberEntity = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException("Member not found"));
    final var bookEntity = bookJPARepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));

    if (bookEntity.getAmount() <= 0) {
      throw new LibraryBadRequestException("Book amount is 0, the book can not be borrowed");
    }

    final var memberBorrowedBooks = new ArrayList<>(memberEntity.getBorrowedBooks());
    final var borrowLimit = libraryBookPropertiesHolder.getBorrowLimit();

    if (memberBorrowedBooks.size() >= borrowLimit) {
      throw new LibraryBadRequestException(
          String.format("A member can borrow many books but not more than %s.", borrowLimit));
    }

    memberBorrowedBooks.add(bookEntity);
    memberEntity.setBorrowedBooks(memberBorrowedBooks);
    bookEntity.setAmount(bookEntity.getAmount() - 1);

    final var updatedMember = memberRepository.save(memberEntity);
    bookJPARepository.save(bookEntity);

    return memberMapper.toMemberReadPojo(updatedMember);
  }

  @Override
  @Transactional
  public MemberReadPojo returnBook(@Nonnull final Long memberId,
                                   @Nonnull final Long bookId) {
    final var memberEntity = memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException("Member not found"));
    final var bookEntity = bookJPARepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));

    final var memberBorrowedBooks = memberEntity.getBorrowedBooks();

    if (memberBorrowedBooks.stream().noneMatch(book -> book.getId().equals(bookEntity.getId()))) {
      throw new LibraryBadRequestException("Member didn't borrow this book");
    }

    ArrayList<BookEntity> updatedMemberBorrowedBooks = memberBorrowedBooks.stream()
        .filter(book -> !book.getId().equals(bookId)).collect(Collectors.toCollection(ArrayList::new));

    memberEntity.setBorrowedBooks(updatedMemberBorrowedBooks);
    bookEntity.setAmount(bookEntity.getAmount() + 1);
    final var updatedMember = memberRepository.save(memberEntity);
    bookJPARepository.save(bookEntity);

    return memberMapper.toMemberReadPojo(updatedMember);
  }
}
