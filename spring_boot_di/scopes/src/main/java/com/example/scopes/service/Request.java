package com.example.scopes.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Scope("request")
public class Request {

  @Autowired
  private HttpServletRequest httpServletRequest;

  private final String now;

  public Request() {
    now = "Request: [time gets updated]" + DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now());
  }

  public String nowRequest() {
    return now + "; " + "PathInfo from http request: `" + httpServletRequest.getPathInfo();
  }
}
