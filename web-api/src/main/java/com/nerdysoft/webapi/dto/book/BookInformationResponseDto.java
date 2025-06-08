package com.nerdysoft.webapi.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class BookInformationResponseDto {
	String title;
	String author;
}
