package com.savdev.demo.sb.service.single_primary;

import com.savdev.demo.sb.api.InjectionWithPrimaryDemoApi;
import org.springframework.stereotype.Service;

@Service
public class InjectionDemoService2 implements InjectionWithPrimaryDemoApi {

  public static final String MSG = "InjectionDemoService_2";

  @Override
  public String getMessage() {
    return MSG;
  }
}
