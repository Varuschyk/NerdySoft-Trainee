package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface LibraryService {

  List<BookReadPojo> getBorrowedBooksByMember(@Nonnull String memberName);

  List<BookReadPojo> getBorrowedBooksByTitle(@Nonnull String title);

  MemberReadPojo borrowBook(@Nonnull Long memberId,
                            @Nonnull Long bookId);

  MemberReadPojo returnBook(@Nonnull Long memberId,
                            @Nonnull Long bookId);
}
