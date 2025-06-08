package com.nerdysoft.webapi.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the request book to create.
 */
@Schema(title = "Book request", description =
    "Representation of the book request.",
    requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class BookRequestDto {
  @Schema(name = "title", description = "The book title.",
      example = "Java",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @Pattern(regexp = "^[A-Z].*$",
      message = "Title must start from capital letter.")
  @NotBlank String title;

  @Schema(name = "author", description = "The author name.",
      example = "Alexander Varushchyk",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+$",
      message = "Author doesn't match required initials. Example: Alexander Varushchyk")
  @NotBlank String author;

  @Schema(name = "amount", description = "The books amount.",
      example = "10",
      requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
  @NotNull Integer amount;
}
