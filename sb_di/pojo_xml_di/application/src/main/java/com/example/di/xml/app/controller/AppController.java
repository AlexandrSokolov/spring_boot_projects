package com.example.di.xml.app.controller;

import com.example.di.xml.app.api.ServiceApi1;
import com.example.di.xml.app.di.ConstructorDi;
import com.example.di.xml.app.di.SetterDi;
import com.example.di.xml.lib.di.LibConstructorDi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class AppController {

  @Autowired
  private LibConstructorDi libConstructorDi;

  private final ServiceApi1 serviceApi1SomeName;

  @Autowired //no setter
  private SetterDi setterDi;

  // @Autowired on setter
  private ConstructorDi constructorDi;

//  public void setSetterDi(SetterDi setterDi) {
//    this.setterDi = setterDi;
//  }

  @Autowired
  public void setConstructorDi(ConstructorDi constructorDi) {
    this.constructorDi = constructorDi;
  }

  public AppController(@Autowired ServiceApi1 serviceApi1SomeName) {
    this.serviceApi1SomeName = serviceApi1SomeName;
  }

  @GetMapping
  public String get() {
    return "AppController#get "
      + libConstructorDi.m1() + " "
      + serviceApi1SomeName.apiCall1() + " "
      + constructorDi.m1() + " "
      + setterDi.m2();
  }
}
