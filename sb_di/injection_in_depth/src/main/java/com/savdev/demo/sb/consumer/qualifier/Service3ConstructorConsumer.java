package com.savdev.demo.sb.consumer.qualifier;

import com.savdev.demo.sb.api.InjectionWithQualifierDemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Service3ConstructorConsumer {

  private final InjectionWithQualifierDemoApi demoApi;

  public Service3ConstructorConsumer(@Autowired @Qualifier("demoService3") InjectionWithQualifierDemoApi demoApi) {
    this.demoApi = demoApi;
  }

  public String msg() {
    return demoApi.getMessage();
  }
}
