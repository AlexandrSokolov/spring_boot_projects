package com.example.di.xml.app.di;

import com.example.di.xml.app.api.ServiceApi1;
import com.example.di.xml.app.api.ServiceApi2;

public class ConstructorDi {

  private final ServiceApi1 serviceApi1SomeName;
  private final ServiceApi2 serviceApi2SomeOtherName;

  public ConstructorDi(
    ServiceApi1 serviceApi1SomeName,
    ServiceApi2 serviceApi2SomeOtherName) {
    this.serviceApi1SomeName = serviceApi1SomeName;
    this.serviceApi2SomeOtherName = serviceApi2SomeOtherName;
  }

  public String m1() {
    return "ConstructorDi#m1 " + serviceApi1SomeName.apiCall1() + " " + serviceApi2SomeOtherName.apiCall2();
  }
}
