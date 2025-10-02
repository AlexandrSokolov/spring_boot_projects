package com.savdev.demo.sb.consumer.injection_ways;

import com.savdev.demo.sb.api.InjectionWithPrimaryDemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldsInjection {

  @Autowired
  private InjectionWithPrimaryDemoApi demoApi;

  public String msg() {
    return demoApi.getMessage();
  }

}
