package com.nerdysoft.apicore.properties;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@Value
@RequiredArgsConstructor
@ConfigurationProperties("library.book")
public class LibraryBookPropertiesHolder {

	@Min(1)
	int borrowLimit;
}
