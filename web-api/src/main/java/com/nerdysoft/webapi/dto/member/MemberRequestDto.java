package com.nerdysoft.webapi.dto.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the member request.
 */
@Schema(title = "Member request", description =
    "Representation of the member request.",
    requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class MemberRequestDto {

  @Schema(name = "name", description = "The member name.",
      example = "Alexander Varushchyk",
      requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 256)
  @NotBlank
  @Size(min = 1, message = "{validation.name.size.too_short}")
  @Size(max = 256, message = "{validation.name.size.too_long}")
  String name;
}
