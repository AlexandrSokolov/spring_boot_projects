package com.example.di.ann.lib.di;

import com.example.di.ann.lib.api.LibServiceApi1;
import com.example.di.ann.lib.api.LibServiceApi2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibSetterDi {

  @Autowired //on field
  private LibServiceApi1 serviceApi1SomeName;

  // no @Autowired
  private LibServiceApi2 serviceApi2SomeOtherName;

  public void setServiceApi1SomeName(LibServiceApi1 serviceApi1SomeName) {
    this.serviceApi1SomeName = serviceApi1SomeName;
  }

  @Autowired //on setter
  public void setServiceApi2SomeOtherName(LibServiceApi2 serviceApi2SomeOtherName) {
    this.serviceApi2SomeOtherName = serviceApi2SomeOtherName;
  }

  public String m2() {
    return "LibSetterDi#m2 " + serviceApi1SomeName.apiCall1() + " " + serviceApi2SomeOtherName.apiCall2();
  }
}
