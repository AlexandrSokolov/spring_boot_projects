package com.savdev.demo.sb.service.multiple;

import com.savdev.demo.sb.api.InjectionWithQualifierDemoApi;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Qualifier("demoService1")
@Primary
public class InjectionWithQualifierService1 implements InjectionWithQualifierDemoApi {

  public static final String MSG = "InjectionWithQualifierService_1";

  @Override
  public String getMessage() {
    return MSG;
  }
}
