package com.nerdysoft.webapi.dto.member;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nerdysoft.webapi.dto.book.BookInformationResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the member borrowed books representation.
 */
@Schema(title = "Member borrowed books response", description =
		"Representation of the member borrowed books response.",
		requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class MemberBorrowedBooksResponseDto {

	@Schema(name = "name", description = "The member name.",
			example = "Alexander Varushchyk",
			requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 256)
	@NotNull
	@Size(min = 1, message = "{validation.name.size.too_short}")
	@Size(max = 256, message = "{validation.name.size.too_long}")
	List<BookInformationResponseDto> borrowedBooks;

	@Schema(name = "size", description = "The borrowed books size.",
			example = "10",
			requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
	@Min(value = 1)
	int size;
}
