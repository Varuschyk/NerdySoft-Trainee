package com.nerdysoft.apicore.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Swagger properties.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Bean defines custom group with declared own parameter.
   *
   * @return configured bean Swagger groups.
   */
  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
        .group("OpenApiController")
        .packagesToScan("com.nerdysoft.apicore")
        .build();
  }
}