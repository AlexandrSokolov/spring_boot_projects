package com.savdev.demo.sb.consumer.injection_ways;

import com.savdev.demo.sb.api.InjectionWithPrimaryDemoApi;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class JakartaInjection {

  @Inject
  private InjectionWithPrimaryDemoApi demoApi;

  public String msg() {
    return demoApi.getMessage();
  }
}
