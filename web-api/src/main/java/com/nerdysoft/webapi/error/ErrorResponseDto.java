package com.nerdysoft.webapi.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Representation of the application error response.
 */
@Schema(title = "Error response", description =
		"Representation of the application error response.",
		requiredMode = Schema.RequiredMode.REQUIRED)
@Value
@Builder
@Jacksonized
@JsonInclude
public class ErrorResponseDto {
	/**
	 * The application error response message.
	 */
	@Schema(name = "errorMessage", description = "The application error response error message.",
			example = "The application user does not have access to the resource.",
			requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 256)
	@NotBlank
	@Size(min = 1, message = "{validation.name.size.too_short}")
	@Size(max = 256, message = "{validation.name.size.too_long}")
	String errorMessage;

	/**
	 * The unique correlation id of the error that can be used by the support team to debug
	 * a particular client error case.
	 */
	@Schema(name = "correlationId",
			description = "The unique correlation id of the error that can be used by the support "
					+ "team to debug a particular client error case.",
			example = "550e8400-e29b-41d4-a716-446655440000",
			requiredMode = Schema.RequiredMode.REQUIRED, minLength = 1, maxLength = 256)
	@NotBlank
	@Size(min = 1, message = "{validation.name.size.too_short}")
	@Size(max = 256, message = "{validation.name.size.too_long}")
	String correlationId;
}
