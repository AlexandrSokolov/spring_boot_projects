package com.example.di.ann.app.service;

import com.example.di.ann.app.api.ServiceApi1;
import org.springframework.stereotype.Component;

@Component
public class ServiceByConstructor implements ServiceApi1 {
  @Override
  public String apiCall1() {
    return "ServiceApi1#apiCall1";
  }
}
