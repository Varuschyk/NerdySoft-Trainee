package com.nerdysoft.apicore.pojo.member;

import java.time.Instant;
import java.util.List;

import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberReadPojo {
  String name;
  Instant membershipDate;
  List<BookReadPojo> booksReadPojo;
}
