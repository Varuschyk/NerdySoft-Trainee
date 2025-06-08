package com.nerdysoft.apicore.exception.book;

import com.nerdysoft.apicore.exception.BadRequestException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * {@link BadRequestException} type for BookEntity fails.
 */
public class BookBadRequestException extends BadRequestException {

	@Serial
	private static final long serialVersionUID = 9213262859342125450L;

	public BookBadRequestException(@Nonnull final String message) {
		super(message);
	}
}
