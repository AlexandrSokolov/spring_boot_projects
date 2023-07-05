package com.example.di.ann.app;

import com.example.di.ann.app.api.ServiceApi1;
import com.example.di.ann.app.api.ServiceApi2;
import com.example.di.ann.app.di.ConstructorDi;
import com.example.di.ann.app.di.SetterDi;
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
@ContextConfiguration(classes = AppContainerConfiguration.class)
class ApplicationTests {

  @Autowired
  private LibServiceApi1 api1;

  @Autowired
  private LibServiceApi2 api2;

  @Autowired
  LibConstructorDi constructorDi;

  @Autowired
  LibSetterDi setterDi;

  @Autowired
  private ServiceApi1 serviceApi1;

  @Autowired
  private ServiceApi2 serviceApi2;

  @Autowired
  ConstructorDi appConstructorDi;

  @Autowired
  SetterDi appSetterDi;

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

  @Test
  public void testServiceApi1() {
    Assertions.assertNotNull(serviceApi1);
    Assertions.assertEquals("ServiceApi1#apiCall1", serviceApi1.apiCall1());
  }

  @Test
  public void testServiceApi2() {
    Assertions.assertNotNull(serviceApi2);
    Assertions.assertEquals("ServiceByFactoryMethod#apiCall1", serviceApi2.apiCall2());
  }

  @Test
  public void testAppConstructorDi() {
    Assertions.assertNotNull(appConstructorDi);
    Assertions.assertEquals(
      "ConstructorDi#m1 ServiceApi1#apiCall1 ServiceByFactoryMethod#apiCall1",
      appConstructorDi.m1());
  }

  @Test
  public void testAppSetterDi() {
    Assertions.assertNotNull(appSetterDi);
    Assertions.assertEquals(
      "SetterDi#m2 ServiceApi1#apiCall1 ServiceByFactoryMethod#apiCall1",
      appSetterDi.m2());
  }

}
