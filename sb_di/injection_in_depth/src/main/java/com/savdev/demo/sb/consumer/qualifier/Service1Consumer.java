package com.savdev.demo.sb.consumer.qualifier;

import com.savdev.demo.sb.api.InjectionWithQualifierDemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service1Consumer {

  @Autowired
  private InjectionWithQualifierDemoApi demoApi;

  public String msg() {
    return demoApi.getMessage();
  }
}
