package com.nerdysoft.apicore.exception;

import jakarta.annotation.Nonnull;
import java.io.Serial;

public class BadRequestException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -4046432236532711478L;

	public BadRequestException(@Nonnull final String message) {
		super(message);
	}

}
