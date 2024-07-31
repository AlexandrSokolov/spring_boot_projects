package com.example.jaxrs_resteasy.rest.config;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static com.example.jaxrs_resteasy.rest.config.JaxRsConfiguration.APPLICATION_PATH;

@Component
@ApplicationPath(APPLICATION_PATH)
public class JaxRsConfiguration extends Application {
  public static final String APPLICATION_PATH = "/rest";
}
