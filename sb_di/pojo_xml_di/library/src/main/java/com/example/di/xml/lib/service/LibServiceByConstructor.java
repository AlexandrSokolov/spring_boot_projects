package com.example.di.xml.lib.service;

import com.example.di.xml.lib.api.LibServiceApi1;

public class LibServiceByConstructor implements LibServiceApi1 {
  @Override
  public String apiCall1() {
    return "LibServiceApi1#apiCall1";
  }
}
