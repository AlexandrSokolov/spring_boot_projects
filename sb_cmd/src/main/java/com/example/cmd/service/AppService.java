package com.example.cmd.service;

import org.springframework.stereotype.Service;

@Service
public class AppService implements ServiceApi {

  @Override
  public void app() {
    System.out.println("`AppService` started");
  }
}
