package com.example.di.xml.app.di;

import com.example.di.xml.app.api.ServiceApi1;
import com.example.di.xml.app.api.ServiceApi2;

public class SetterDi {

  private ServiceApi1 serviceApi1SomeName;
  private ServiceApi2 serviceApi2SomeOtherName;

  public void setServiceApi1SomeName(ServiceApi1 serviceApi1SomeName) {
    this.serviceApi1SomeName = serviceApi1SomeName;
  }

  public void setServiceApi2SomeOtherName(ServiceApi2 serviceApi2SomeOtherName) {
    this.serviceApi2SomeOtherName = serviceApi2SomeOtherName;
  }

  public String m2() {
    return "SetterDi#m2 " + serviceApi1SomeName.apiCall1() + " " + serviceApi2SomeOtherName.apiCall2();
  }
}
