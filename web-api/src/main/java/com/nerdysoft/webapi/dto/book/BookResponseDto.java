package com.nerdysoft.webapi.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the book response.
 */
@Schema(title = "Book response", description =
    "Representation of the book response.",
    requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class BookResponseDto {

  @Schema(name = "title", description = "The book title.",
      example = "Java",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank String title;

  @Schema(name = "author", description = "The author name.",
      example = "Alexander Varushchyk",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @NotBlank String author;

  @Schema(name = "amount", description = "The books amount.",
      example = "10",
      requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
  @NotNull Integer amount;
}
