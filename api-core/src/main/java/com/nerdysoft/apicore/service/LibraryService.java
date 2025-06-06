package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import jakarta.annotation.Nonnull;

public interface LibraryService {

  MemberReadPojo borrowBook(@Nonnull Long memberId,
                            @Nonnull Long bookId);

  MemberReadPojo returnBook(@Nonnull Long memberId,
                            @Nonnull Long bookId);
}
