package com.example.aop.decorator;

import com.example.aop.decorator.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements  ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private ServiceApi serviceApi;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent ignored) {
    System.out.println(
      serviceApi.fromId(10));
  }
}
