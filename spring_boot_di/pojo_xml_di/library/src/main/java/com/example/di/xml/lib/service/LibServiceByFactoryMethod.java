package com.example.di.xml.lib.service;

import com.example.di.xml.lib.api.LibServiceApi2;

public class LibServiceByFactoryMethod implements LibServiceApi2 {

  private static final LibServiceByFactoryMethod INSTANCE = new LibServiceByFactoryMethod();

  public static final LibServiceByFactoryMethod instance() {
    return INSTANCE;
  }

  private LibServiceByFactoryMethod() { }

  @Override
  public String apiCall2() {
    return "LibServiceByFactoryMethod#apiCall1";
  }
}
