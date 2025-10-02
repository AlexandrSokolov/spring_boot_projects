package com.savdev.demo.sb.service;

import com.savdev.demo.sb.consumer.qualifier.*;
import com.savdev.demo.sb.service.multiple.InjectionWithQualifierCustomAnnotationService;
import com.savdev.demo.sb.service.multiple.InjectionWithQualifierService1;
import com.savdev.demo.sb.service.multiple.InjectionWithQualifierService2;
import com.savdev.demo.sb.service.multiple.InjectionWithQualifierService3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MultipleBeanTypesInjectionTest {

  @Autowired private Service1Consumer service1;

  @Autowired private Service2FieldConsumer service2;

  @Autowired private Service3ConstructorConsumer service3;

  @Autowired private Service3WithoutQualifierConsumer withoutQualifierConsumer;

  @Autowired private Service4WithQualifierCustomAnnotationConsumer service4;

  @Test
  public void testPrimaryInjection() {
    assertEquals(InjectionWithQualifierService1.MSG, service1.msg());
  }

  @Test
  public void testQualifier_FieldInjection() {
    assertEquals(InjectionWithQualifierService2.MSG, service2.msg());
  }

  @Test
  public void testQualifier_ConstructorInjection() {
    assertEquals(InjectionWithQualifierService3.MSG, service3.msg());
  }

  @Test
  public void testNoQualifier_BeanImplementationInjection() {
    assertEquals(InjectionWithQualifierService3.MSG, withoutQualifierConsumer.msg());
  }

  @Test
  public void testQualifierCustomAnnotation() {
    assertEquals(InjectionWithQualifierCustomAnnotationService.MSG, service4.msg());
  }
}
