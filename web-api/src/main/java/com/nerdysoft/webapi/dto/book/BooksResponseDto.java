package com.nerdysoft.webapi.dto.book;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the borrowed books response.
 */
@Schema(title = "Books response", description =
    "Representation of the borrowed books response.",
    requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class BooksResponseDto {

  @Schema(name = "books", description = "The borrowed books.",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @NotNull
  List<BookResponseDto> books;

  @Schema(name = "memberId", description = "The borrowed books size.",
      example = "1",
      requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
  int borrowedSize;
}