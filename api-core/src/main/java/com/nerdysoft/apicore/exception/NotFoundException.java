package com.nerdysoft.apicore.exception;

import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * Global NotFoundException type for fails when resource was not found.
 */
public class NotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 5334182604462601188L;

	public NotFoundException(@Nonnull final String message) {
		super(message);
	}
}
