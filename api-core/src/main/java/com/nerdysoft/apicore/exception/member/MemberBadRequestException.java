package com.nerdysoft.apicore.exception.member;

import com.nerdysoft.apicore.exception.BadRequestException;
import jakarta.annotation.Nonnull;

import java.io.Serial;

public class MemberBadRequestException extends BadRequestException {

	@Serial
	private static final long serialVersionUID = 449737743365988417L;

	public MemberBadRequestException(@Nonnull final String message) {
		super(message);
	}
}
