package com.nerdysoft.webapi.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class BookRequestDto {
  @Pattern(regexp = "^[A-Z].*$",
      message = "Title must start from capital letter.")
  @NotBlank String title;
  @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+$",
      message = "Author doesn't match required initials. Example: Olekandr Varush")
  @NotBlank String author;
  @NotNull Integer amount;
}
