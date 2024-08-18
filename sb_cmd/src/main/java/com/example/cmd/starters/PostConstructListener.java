package com.example.cmd.starters;

import com.example.cmd.service.ServiceApi;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConstructListener {

  @Autowired
  private ServiceApi serviceApi;

  @PostConstruct
  public void init() {
    System.out.print("PostConstructListener init: ");
    serviceApi.app();
  }
}
