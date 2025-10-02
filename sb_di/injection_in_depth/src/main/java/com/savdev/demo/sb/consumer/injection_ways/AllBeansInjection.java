package com.savdev.demo.sb.consumer.injection_ways;

import com.savdev.demo.sb.api.InjectionWithPrimaryDemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AllBeansInjection {

  @Autowired
  private Map<String, InjectionWithPrimaryDemoApi> injectionDemoApiMap;

  public Map<String, InjectionWithPrimaryDemoApi> getInjectionDemoApiMap() {
    return injectionDemoApiMap;
  }
}
