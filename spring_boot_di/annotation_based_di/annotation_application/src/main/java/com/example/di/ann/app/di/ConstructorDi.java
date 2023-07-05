package com.example.di.ann.app.di;

import com.example.di.ann.app.api.ServiceApi1;
import com.example.di.ann.app.api.ServiceApi2;
import org.springframework.stereotype.Component;

@Component
public class ConstructorDi {

  private final ServiceApi1 serviceApi1SomeName;
  private final ServiceApi2 serviceApi2SomeOtherName;

  //with a single constructor even without @Autowired will work
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
