package com.nerdysoft.webapi.dto.library;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the request book to borrow.
 */
@Schema(title = "Borrow book request", description =
    "Representation of the borrow book request.",
    requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class BorrowBooksRequestDto {

  @Schema(name = "memberId", description = "The member id.",
      example = "1",
      requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
  @NotNull
  Long memberId;

  @Schema(name = "bookId", description = "The book id.",
      example = "1",
      requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
  @NotNull
  Long bookId;
}
