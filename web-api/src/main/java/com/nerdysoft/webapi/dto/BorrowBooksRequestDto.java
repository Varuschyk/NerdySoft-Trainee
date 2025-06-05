package com.nerdysoft.webapi.dto;

import java.util.List;

public class BorrowBooksRequestDto {
  List<Long> bookIds;
  Long memberId;
}
