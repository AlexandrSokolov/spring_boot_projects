package com.example.di.ann.app.di;

import com.example.di.ann.app.api.ServiceApi1;
import com.example.di.ann.app.api.ServiceApi2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetterDi {

  @Autowired
  private ServiceApi1 serviceApi1SomeName;
  //@Autowired on setter
  private ServiceApi2 serviceApi2SomeOtherName;

  public void setServiceApi1SomeName(ServiceApi1 serviceApi1SomeName) {
    this.serviceApi1SomeName = serviceApi1SomeName;
  }

  @Autowired //makes the dependency required
  public void setServiceApi2SomeOtherName(ServiceApi2 serviceApi2SomeOtherName) {
    this.serviceApi2SomeOtherName = serviceApi2SomeOtherName;
  }

  public String m2() {
    return "SetterDi#m2 " + serviceApi1SomeName.apiCall1() + " " + serviceApi2SomeOtherName.apiCall2();
  }
}
