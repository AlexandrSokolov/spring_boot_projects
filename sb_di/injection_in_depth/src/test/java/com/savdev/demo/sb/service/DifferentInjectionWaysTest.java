package com.savdev.demo.sb.service;

import com.savdev.demo.sb.consumer.injection_ways.*;
import com.savdev.demo.sb.service.single_primary.InjectionDemoService1Primary;import com.savdev.demo.sb.service.single_primary.InjectionDemoService2;import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DifferentInjectionWaysTest {

  @Autowired
  private ConstructorInjection constructorInjection;

  @Autowired
  private SetterInjection setterInjection;

  @Autowired
  private FieldsInjection fieldsInjection;

  @Autowired
  private JakartaInjection jakartaInjection;

  @Autowired
  private AllBeansInjection allBeansInjection;

  @Test
  void testConstructorInjection() {
    assertEquals(InjectionDemoService1Primary.MSG, constructorInjection.msg());
  }

  @Test
  void testSetterInjection() {
    assertEquals(InjectionDemoService1Primary.MSG, setterInjection.msg());
  }

  @Test
  void testFieldsInjection() {
    assertEquals(InjectionDemoService1Primary.MSG, fieldsInjection.msg());
  }

  @Test
  void testJakartaInjection() {
    assertEquals(InjectionDemoService1Primary.MSG, jakartaInjection.msg());
  }

  @Test
  void testAllBeansInjection() {
    var b = allBeansInjection.getInjectionDemoApiMap();
    assertEquals(3, b.size());
    //for instance InjectionDemoService2 is stored as `injectionDemoService2` key:
    assertTrue(b.containsKey(
      InjectionDemoService2.class.getSimpleName().substring(0, 1).toLowerCase()
      + InjectionDemoService2.class.getSimpleName().substring(1)));
  }
}