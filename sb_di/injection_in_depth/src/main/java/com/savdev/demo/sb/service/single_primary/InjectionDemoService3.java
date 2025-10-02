package com.savdev.demo.sb.service.single_primary;

import com.savdev.demo.sb.api.InjectionWithPrimaryDemoApi;
import org.springframework.stereotype.Service;

@Service
public class InjectionDemoService3 implements InjectionWithPrimaryDemoApi {

  public static final String MSG = "InjectionDemoService_3";

  @Override
  public String getMessage() {
    return MSG;
  }
}
