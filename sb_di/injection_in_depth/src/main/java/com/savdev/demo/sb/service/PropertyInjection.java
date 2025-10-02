package com.savdev.demo.sb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertyInjection {

  @Value("${app.test.q1}")
  String q1;

  public String q1() {
    return q1;
  }

  public String q2() {
    return "${app.test.q2}";
  }
}
