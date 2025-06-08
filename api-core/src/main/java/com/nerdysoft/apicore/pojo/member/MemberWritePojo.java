package com.nerdysoft.apicore.pojo.member;

import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Represents a simple pojo that encapsulates member write data.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberWritePojo {
  String name;
}
