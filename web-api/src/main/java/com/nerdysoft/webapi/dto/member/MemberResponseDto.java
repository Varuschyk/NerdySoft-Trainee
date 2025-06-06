package com.nerdysoft.webapi.dto.member;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nerdysoft.webapi.dto.book.BookResponseDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class MemberResponseDto {
  String name;
  Instant membershipDate;
  List<BookResponseDto> book;
}
