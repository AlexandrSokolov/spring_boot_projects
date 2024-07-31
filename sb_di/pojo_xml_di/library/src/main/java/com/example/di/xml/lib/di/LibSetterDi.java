package com.example.di.xml.lib.di;

import com.example.di.xml.lib.api.LibServiceApi1;
import com.example.di.xml.lib.api.LibServiceApi2;

public class LibSetterDi {

  private LibServiceApi1 serviceApi1SomeName;
  private LibServiceApi2 serviceApi2SomeOtherName;

  public void setServiceApi1SomeName(LibServiceApi1 serviceApi1SomeName) {
    this.serviceApi1SomeName = serviceApi1SomeName;
  }

  public void setServiceApi2SomeOtherName(LibServiceApi2 serviceApi2SomeOtherName) {
    this.serviceApi2SomeOtherName = serviceApi2SomeOtherName;
  }

  public String m2() {
    return "LibSetterDi#m2 " + serviceApi1SomeName.apiCall1() + " " + serviceApi2SomeOtherName.apiCall2();
  }
}
