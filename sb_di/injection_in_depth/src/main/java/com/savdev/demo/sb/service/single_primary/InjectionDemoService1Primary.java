package com.savdev.demo.sb.service.single_primary;

import com.savdev.demo.sb.api.InjectionWithPrimaryDemoApi;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class InjectionDemoService1Primary implements InjectionWithPrimaryDemoApi {

  public static final String MSG = "InjectionDemoService.PRIMARY";

  @Override
  public String getMessage() {
    return MSG;
  }
}
