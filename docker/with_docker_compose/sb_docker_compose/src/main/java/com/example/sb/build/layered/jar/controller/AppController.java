package com.example.sb.build.layered.jar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class AppController {

  @GetMapping
  public String hello() {
    return "Docker compose application!";
  }
}
