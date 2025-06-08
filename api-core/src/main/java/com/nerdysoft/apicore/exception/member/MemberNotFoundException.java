package com.nerdysoft.apicore.exception.member;

import com.nerdysoft.apicore.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

public class MemberNotFoundException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = -1611955840789210062L;

	public MemberNotFoundException(@Nonnull final String message) {
		super(message);
	}
}
