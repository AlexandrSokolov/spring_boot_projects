package com.example.di.xml.app.service;

import com.example.di.xml.app.api.ServiceApi1;

public class ServiceByConstructor implements ServiceApi1 {
  @Override
  public String apiCall1() {
    return "ServiceApi1#apiCall1";
  }
}
