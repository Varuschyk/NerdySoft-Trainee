package com.nerdysoft.apicore.pojo.member;

import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberWritePojo {
  String name;
  List<Long> bookIds;
}
