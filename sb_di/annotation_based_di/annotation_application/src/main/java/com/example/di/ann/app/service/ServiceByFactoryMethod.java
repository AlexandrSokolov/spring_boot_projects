package com.example.di.ann.app.service;

import com.example.di.ann.app.api.ServiceApi2;
import org.springframework.stereotype.Service;

@Service
public class ServiceByFactoryMethod implements ServiceApi2 {

  private static final ServiceByFactoryMethod INSTANCE = new ServiceByFactoryMethod();

  public static final ServiceByFactoryMethod instance() {
    return INSTANCE;
  }

  private ServiceByFactoryMethod() { }

  @Override
  public String apiCall2() {
    return "ServiceByFactoryMethod#apiCall1";
  }
}
