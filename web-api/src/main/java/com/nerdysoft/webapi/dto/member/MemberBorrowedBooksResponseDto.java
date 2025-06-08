package com.nerdysoft.webapi.dto.member;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nerdysoft.webapi.dto.book.BookInformationResponseDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class MemberBorrowedBooksResponseDto {
	List<BookInformationResponseDto> borrowedBooks;
	int size;
}
