package com.nerdysoft.apicore.exception.book;

import com.nerdysoft.apicore.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * {@link NotFoundException} type for BookEntity fails.
 */
public class BookNotFoundException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = -7223770988184467945L;

	public BookNotFoundException(@Nonnull final String message) {
		super(message);
	}
}
