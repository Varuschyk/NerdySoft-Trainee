package com.nerdysoft.apicore.exception.library;

import com.nerdysoft.apicore.exception.BadRequestException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

public class LibraryBadRequestException extends BadRequestException {

	@Serial
	private static final long serialVersionUID = -2018949060903747513L;

	public LibraryBadRequestException(@Nonnull final String message) {
		super(message);
	}
}
