package com.example.jpa.liquibase.rest.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.springframework.stereotype.Component;


@Component
@ApplicationPath(RestAppConfiguration.APPLICATION_PATH)
public class RestAppConfiguration extends Application {
  public static final String APPLICATION_PATH = "/rest";
}
