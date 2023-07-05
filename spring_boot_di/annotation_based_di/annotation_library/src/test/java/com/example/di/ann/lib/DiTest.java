package com.example.di.ann.lib;

import com.example.di.ann.lib.api.LibServiceApi1;
import com.example.di.ann.lib.api.LibServiceApi2;
import com.example.di.ann.lib.di.LibConstructorDi;
import com.example.di.ann.lib.di.LibSetterDi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = AnnotationLibraryConfiguration.class)
public class DiTest {

  @Autowired
  private LibServiceApi1 api1;

  @Autowired
  private LibServiceApi2 api2;

  @Autowired
  LibConstructorDi constructorDi;

  @Autowired
  LibSetterDi setterDi;

  @Test
  public void testApi1() {
    Assertions.assertNotNull(api1);
    Assertions.assertEquals("LibServiceApi1#apiCall1", api1.apiCall1());
  }

  @Test
  public void testApi2() {
    Assertions.assertNotNull(api2);
    Assertions.assertEquals("LibServiceByFactoryMethod#apiCall1", api2.apiCall2());
  }

  @Test
  public void testConstructorDi() {
    Assertions.assertNotNull(constructorDi);
    Assertions.assertEquals(
      "LibConstructorDi#m1 LibServiceApi1#apiCall1 LibServiceByFactoryMethod#apiCall1",
      constructorDi.m1());
  }

  @Test
  public void testSetterDi() {
    Assertions.assertNotNull(setterDi);
    Assertions.assertEquals(
      "LibSetterDi#m2 LibServiceApi1#apiCall1 LibServiceByFactoryMethod#apiCall1",
      setterDi.m2());
  }
}
