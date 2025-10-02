package com.savdev.demo.sb.consumer.qualifier;

import com.savdev.demo.sb.api.InjectionWithQualifierDemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Service2FieldConsumer {

  @Autowired
  @Qualifier("demoService2")
  private InjectionWithQualifierDemoApi demoApi;

  public String msg() {
    return demoApi.getMessage();
  }
}
