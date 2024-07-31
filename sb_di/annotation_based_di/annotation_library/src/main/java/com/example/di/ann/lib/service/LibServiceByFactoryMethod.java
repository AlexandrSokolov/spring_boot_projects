package com.example.di.ann.lib.service;

import com.example.di.ann.lib.api.LibServiceApi2;
import org.springframework.stereotype.Service;

@Service
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
