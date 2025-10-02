package com.savdev.demo.sb.consumer.qualifier;

import com.savdev.demo.sb.service.multiple.InjectionWithQualifierService3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service3WithoutQualifierConsumer {

  @Autowired
  private InjectionWithQualifierService3 service3;

  public String msg() {
    return service3.getMessage();
  }
}
