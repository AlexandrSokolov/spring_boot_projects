package com.example.scopes.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Scope("singleton")
public class SingetonRequestAttributes {

  private final String now;

  public SingetonRequestAttributes() {
    now = "SingetonRequestAttributes: [same time]" + DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now());
  }


  public String nowSingetonRequestAttributes() {
    return now + "; " +
      Optional.ofNullable(RequestContextHolder.getRequestAttributes())
        .filter(a -> a instanceof ServletRequestAttributes)
        .map(a -> (ServletRequestAttributes) a)
        .map(ServletRequestAttributes::getRequest)
        .map(httpServletRequest -> "PathInfo from http request: `" + httpServletRequest.getPathInfo() + "`")
        .orElse("No http request data");
  }
}
