package com.nerdysoft.apicore.pojo.book;

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
public class BookWritePojo {
  String title;
  String author;
  Integer amount;
}
