package com.nerdysoft.apicore.configuration.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration for scanning entities and creation repositories.
 */
@Configuration
@EnableJpaRepositories("com.nerdysoft.apicore.persistence.repository")
@EntityScan("com.nerdysoft")
public class JpaConfiguration {
}
