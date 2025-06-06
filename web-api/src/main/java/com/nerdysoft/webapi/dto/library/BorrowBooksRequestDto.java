package com.nerdysoft.webapi.dto.library;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class BorrowBooksRequestDto {
  Long memberId;
  List<Long> bookIds;
}
