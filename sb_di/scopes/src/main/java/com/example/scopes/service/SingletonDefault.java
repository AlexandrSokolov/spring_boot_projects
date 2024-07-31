package com.example.scopes.service;

import com.example.scopes.api.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SingletonDefault {

  /*
   * It is not allowed to inject scope with `request` scope into the bean with `singleton` scope
   *
   * Throws:
   * java.lang.IllegalStateException: No thread-bound request found:
   * Are you referring to request attributes outside of an actual web request,
   *    or processing a request outside of the originally receiving thread?
   *
  @Autowired
  private Request request;
  */

  /**
   * You can inject singeton and access request data via `RequestContextHolder.getRequestAttributes()`
   */
  @Autowired
  private SingetonRequestAttributes singetonRequestAttributes;

  /**
   * ServiceApi has 2 implementations:
   *  `RequestServiceApi` with request scope, and it is not possible to inject such component into singleton
   *  and
   *  `AppConfig#serviceApi` that provides `ServiceApi` implementation and defined with a default scope.
   *    Exactly the last bean from `AppConfig#serviceApi` is injected
   */
  @Autowired
  private ServiceApi serviceApi;

  private final String now;

  public SingletonDefault() {
    now = "SingletonDefault: [same time]" + DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now());
  }

  public String nowSingleton() {
    return now + System.lineSeparator()
      + "; from SingletonDefault: "
      + singetonRequestAttributes.nowSingetonRequestAttributes()
      + System.lineSeparator()
      + "; from SingletonDefault:"
      + serviceApi.serviceApiMethod();
  }
}
