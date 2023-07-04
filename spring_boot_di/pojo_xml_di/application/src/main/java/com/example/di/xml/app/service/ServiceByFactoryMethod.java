package com.example.di.xml.app.service;

import com.example.di.xml.app.api.ServiceApi2;

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
