package com.nerdysoft.apicore.pojo.book;

import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Represents a simple pojo that encapsulates book read data.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookReadPojo {
  String title;
  String author;
  Integer amount;
}
