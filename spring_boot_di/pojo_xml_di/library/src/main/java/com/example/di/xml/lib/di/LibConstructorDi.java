package com.example.di.xml.lib.di;

import com.example.di.xml.lib.api.LibServiceApi1;
import com.example.di.xml.lib.api.LibServiceApi2;

public class LibConstructorDi {

  private final LibServiceApi1 serviceApi1SomeName;
  private final LibServiceApi2 serviceApi2SomeOtherName;

  public LibConstructorDi(
    LibServiceApi1 serviceApi1SomeName,
    LibServiceApi2 serviceApi2SomeOtherName) {
    this.serviceApi1SomeName = serviceApi1SomeName;
    this.serviceApi2SomeOtherName = serviceApi2SomeOtherName;
  }

  public String m1() {
    return "LibConstructorDi#m1 " + serviceApi1SomeName.apiCall1() + " " + serviceApi2SomeOtherName.apiCall2();
  }
}
