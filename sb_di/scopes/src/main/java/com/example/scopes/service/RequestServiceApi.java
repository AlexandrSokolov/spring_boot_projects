package com.example.scopes.service;

import com.example.scopes.api.ServiceApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Scope("request")
public class RequestServiceApi implements ServiceApi {

  private final String now;

  public RequestServiceApi() {
    now = "RequestServiceApi: [time gets updated]" + DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now());
  }

  @Override
  public String serviceApiMethod() {
    return now;
  }
}
