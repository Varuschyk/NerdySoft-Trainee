package com.nerdysoft.webapi.dto.member;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the member.
 */
@Schema(title = "Member response", description =
    "Representation of the member response.",
    requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class MemberResponseDto {

  @Schema(name = "name", description = "The member name.",
      example = "Alexander Varushchyk",
      requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 256)
  @NotBlank
  @Size(min = 1, message = "{validation.name.size.too_short}")
  @Size(max = 256, message = "{validation.name.size.too_long}")
  String name;

  @Schema(name = "membershipDate", description = "The membership date.",
      requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 256)
  @NotNull
  @Size(min = 1, message = "{validation.name.size.too_short}")
  @Size(max = 256, message = "{validation.name.size.too_long}")
  Instant membershipDate;

  @Schema(name = "borrowedBooks", description = "The borrowed books from the library",
      requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 256)
  @NotNull
  @Size(min = 1, message = "{validation.name.size.too_short}")
  @Size(max = 256, message = "{validation.name.size.too_long}")
  MemberBorrowedBooksResponseDto borrowedBooks;
}

