package com.example.aop.decorator.service;

import org.springframework.stereotype.Service;

@Service
public class AppService implements ServiceApi {

  @Override
  public String fromId(Integer id) {
    return "from id: " + id;
  }
}
