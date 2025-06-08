package com.nerdysoft.apicore.service;

import java.util.List;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import jakarta.annotation.Nonnull;

/**
 * Abstraction library services.
 */
public interface LibraryService {

  /**
   * Retrieves the borrowed books by the given member name.
   *
   * @param memberName the member name registered in library.
   * @return the borrowed books by the given member name.
   */
  List<BookReadPojo> getBorrowedBooksByMember(@Nonnull String memberName);

  /**
   * Retrieves the borrowed books by the given book title.
   *
   * @param title the book title registered in library.
   * @return the borrowed books by the given title.
   */
  List<BookReadPojo> getBorrowedBooksByTitle(@Nonnull String title);

  /**
   * Borrows book by the given id to specified member.
   *
   * @param memberId the member id to borrow book.
   * @param bookId the book id that need to borrow.
   * @return the member with borrowed book.
   */
  MemberReadPojo borrowBook(@Nonnull Long memberId,
                            @Nonnull Long bookId);

  /**
   * Returns the specified book from member.
   *
   * @param memberId the member id that returns book.
   * @param bookId the book id that need to return.
   * @return the member without returned book.
   */
  MemberReadPojo returnBook(@Nonnull Long memberId,
                            @Nonnull Long bookId);
}
