package com.nerdysoft.apicore.configuration.handler;

import java.util.UUID;
import com.nerdysoft.apicore.exception.BadRequestException;
import com.nerdysoft.apicore.exception.NotFoundException;
import com.nerdysoft.webapi.error.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * It is used to create a global exception handler that returns a JSON response.
 * It is to return a specific error response in case of an exception.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * The constraint violation exception handler that handles all the exceptions
	 * resulted from exceptional situations when provided wrong data.
	 *
	 * @param request the request that failed and was interrupted by the exception.
	 * @param exception the cause exception that interrupted request processing.
	 * @return the error response that will be returned to the client.
	 */
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleUnauthorizedException(
			@NonNull final HttpServletRequest request,
			@NonNull final ConstraintViolationException exception) {
		final var correlationId = UUID.randomUUID();
		log.error("Can not process the request due to data validation error. Correlation id={}",
				correlationId, exception);
		return ErrorResponseDto.builder()
				.errorMessage(
						"Validation error happened while processing the request. "
								+ "The user provided wrong data.")
				.correlationId(correlationId.toString())
				.build();
	}

	/**
	 * The not found exception handler that handles all the exceptions
	 * resulted from exceptional situations when the requested resource was not found.
	 *
	 * @param request the request that failed and was interrupted by the exception.
	 * @param exception the cause exception that interrupted request processing.
	 * @return the error response that will be returned to the client.
	 */
	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleNotFoundException(
			@NonNull final HttpServletRequest request,
			@NonNull final NotFoundException exception) {
		final var correlationId = UUID.randomUUID();
		log.error("Can not find the requested resource. The correlation id={}.", correlationId,
				exception);
		return ErrorResponseDto.builder()
				.errorMessage("Can not find the requested resource.")
				.correlationId(correlationId.toString())
				.build();
	}

	/**
	 * The bad request exception handler that handles all the exceptions
	 * resulted from exceptional situations when the requested resource
	 * process gone wrong due to wrong client request to resource.
	 *
	 * @param request the request that failed and was interrupted by the exception.
	 * @param exception the cause exception that interrupted request processing.
	 * @return the error response that will be returned to the client.
	 */
	@ResponseBody
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleDuplicateException(
			@NonNull final HttpServletRequest request,
			@NonNull final Exception exception) {
		final var correlationId = UUID.randomUUID();
		log.error("Client made malformed request. Correlation id={}.",
				correlationId, exception);
		return ErrorResponseDto.builder()
				.errorMessage("Client made malformed request.")
				.correlationId(correlationId.toString())
				.build();
	}

	/**
	 * The last resort exception handler that handles all the exceptions
	 * that were not handled by other exception handlers.
	 *
	 * @param request the request that failed and was interrupted by the exception.
	 * @param exception the cause exception that interrupted request processing.
	 * @return the error response that will be returned to the client.
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseDto handleException(
			@NonNull final HttpServletRequest request,
			@NonNull final Exception exception) {
		final var correlationId = UUID.randomUUID();
		log.error("Can not process the request due to an unexpected error. Correlation id={}.",
				correlationId, exception);
		return ErrorResponseDto.builder()
				.errorMessage("Unexpected error happened while processing the request.")
				.correlationId(correlationId.toString())
				.build();
	}

}
