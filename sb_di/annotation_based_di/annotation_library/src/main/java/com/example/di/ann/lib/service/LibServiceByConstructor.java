package com.example.di.ann.lib.service;

import com.example.di.ann.lib.api.LibServiceApi1;
import org.springframework.stereotype.Component;

@Component
public class LibServiceByConstructor implements LibServiceApi1 {
  @Override
  public String apiCall1() {
    return "LibServiceApi1#apiCall1";
  }
}
