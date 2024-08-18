package com.example.aop.decorator.service;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import org.springframework.stereotype.Service;

@Decorator
@Service
public class Decorator1 implements ServiceApi {

  @Delegate
  private ServiceApi serviceApi;

  @Override
  public String fromId(Integer id) {
    return "decorated: " + serviceApi.fromId(id);
  }
}
