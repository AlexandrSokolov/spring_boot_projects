package com.savdev.demo.sb.service.multiple;

import com.savdev.demo.sb.api.InjectionWithQualifierDemoApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("demoService2")
public class InjectionWithQualifierService2 implements InjectionWithQualifierDemoApi {

  public static final String MSG = "InjectionWithQualifierService_2";

  @Override
  public String getMessage() {
    return MSG;
  }
}
