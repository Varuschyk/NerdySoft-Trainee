package com.nerdysoft.apicore.service.impl;

import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.persistence.repository.BookJPARepository;
import com.nerdysoft.apicore.persistence.repository.MemberJPARepository;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.service.LibraryService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

  @Value("${library.book.borrow-limit}")
  private static int availableCountToBorrowBooks;

  private final BookJPARepository bookJPARepository;
  private final MemberJPARepository memberRepository;
  private final MemberMapper memberMapper;

  @Override
  @Transactional
  public MemberReadPojo borrowBook(@Nonnull final Long memberId,
                                   @Nonnull final Long bookId) {
    final var memberEntity = memberRepository.findById(memberId)
        .orElseThrow(() -> new RuntimeException("Member not found"));
    final var bookEntity = bookJPARepository.findById(bookId)
        .orElseThrow(() -> new RuntimeException("Book not found"));

    if (bookEntity.getAmount() <= 0) {
      throw new RuntimeException("Book amount is 0, the book can not be borrowed");
    }

    final var memberBorrowedBooks = memberEntity.getBorrowedBooks();

    if (memberBorrowedBooks.size() >= availableCountToBorrowBooks) {
      throw new RuntimeException(
          String.format("A member can borrow many books but not more than %s.", availableCountToBorrowBooks));
    }

    memberBorrowedBooks.add(bookEntity);
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
        .orElseThrow(() -> new RuntimeException("Member not found"));
    final var bookEntity = bookJPARepository.findById(bookId)
        .orElseThrow(() -> new RuntimeException("Book not found"));

    final var memberBorrowedBooks = memberEntity.getBorrowedBooks();

    if (memberBorrowedBooks.stream().noneMatch(book -> book.getId().equals(bookEntity.getId()))) {
      throw new RuntimeException("Member didn't borrow this book");
    }

    memberBorrowedBooks.remove(bookEntity);
    bookEntity.setAmount(bookEntity.getAmount() + 1);
    final var updatedMember = memberRepository.save(memberEntity);
    bookJPARepository.save(bookEntity);

    return memberMapper.toMemberReadPojo(updatedMember);
  }
}
