package com.savdev.demo.sb.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertiesInjectionTest {

  public static final String q1 = "test property value 1";
  public static final String q2 = "test property value 2";

  @Autowired
  private PropertyInjection propertyInjection;

  @Test
  public void testPropertyInjection() {
    Assertions.assertEquals(q1, propertyInjection.q1());
    Assertions.assertEquals(q2, propertyInjection.q2());
  }
}
