package com.nerdysoft.apicore.pojo.member;

import java.time.Instant;
import com.nerdysoft.webapi.dto.member.MemberBorrowedBooksResponseDto;
import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Represents a simple pojo that encapsulates member read data.
 */
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
