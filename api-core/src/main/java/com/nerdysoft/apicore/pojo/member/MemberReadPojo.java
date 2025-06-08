package com.nerdysoft.apicore.pojo.member;

import com.nerdysoft.webapi.dto.member.MemberBorrowedBooksResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberReadPojo {
  String name;
  Instant membershipDate;
  MemberBorrowedBooksResponseDto borrowedBooks;
}
