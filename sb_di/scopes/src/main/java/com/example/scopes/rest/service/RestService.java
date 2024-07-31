package com.example.scopes.rest.service;


import com.example.scopes.api.RestApi;
import com.example.scopes.service.Request;
import com.example.scopes.service.SingletonDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request") //if we want an ability to inject request-scoped beans
public class RestService implements RestApi {

  @Autowired
  private SingletonDefault singleton;

  @Autowired
  private Request request;

  @Override
  public String scope() {
    return singleton.nowSingleton() + "\n"
      + request.nowRequest();
  }

}
