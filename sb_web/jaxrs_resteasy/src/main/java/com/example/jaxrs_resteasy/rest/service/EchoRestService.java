package com.example.jaxrs_resteasy.rest.service;

import com.example.jaxrs_resteasy.rest.api.EchoRestApi;
import org.springframework.stereotype.Component;

@Component
public class EchoRestService implements EchoRestApi {

  @Override
  public String echo(String message) {
    return message;
  }
}
