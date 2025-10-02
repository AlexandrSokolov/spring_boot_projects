package com.savdev.demo.sb.consumer.qualifier;

import com.savdev.demo.sb.api.InjectionWithQualifierDemoApi;
import com.savdev.demo.sb.service.multiple.QualifierService4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service4WithQualifierCustomAnnotationConsumer {

  @Autowired
  @QualifierService4
  private InjectionWithQualifierDemoApi demoApi;

  public String msg() {
    return demoApi.getMessage();
  }
}
