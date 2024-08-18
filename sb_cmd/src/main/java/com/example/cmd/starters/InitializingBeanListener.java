package com.example.cmd.starters;

import com.example.cmd.service.ServiceApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanListener implements InitializingBean {

  @Autowired
  private ServiceApi serviceApi;

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.print("InitializingBeanListener afterPropertiesSet: ");
    serviceApi.app();
  }
}
