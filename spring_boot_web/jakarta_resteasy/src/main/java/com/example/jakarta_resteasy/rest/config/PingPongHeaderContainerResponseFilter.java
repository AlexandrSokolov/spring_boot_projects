package com.example.jakarta_resteasy.rest.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Provider
public class PingPongHeaderContainerResponseFilter implements ContainerResponseFilter {

  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

    // Checks if request has a HTTP header named "ping".
    // If it does, adds an HTTP header named "pong" to the response.
    // The header value is irrelevant.
    Optional.ofNullable(requestContext.getHeaderString("ping")).ifPresent(value ->
      responseContext.getHeaders().add("pong", value));
  }

}