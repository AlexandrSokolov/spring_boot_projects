package com.example.cmd.starters;

import com.example.cmd.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationEventListener {

  @Autowired
  private ServiceApi serviceApi;

  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent ignored) {
    System.out.print("StartupApplicationEventListener onApplicationEvent: ");
    serviceApi.app();
  }
}
