package com.example.external;

import com.example.external.config.AppExternalConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.external.config.AppExternalConfiguration.SYSTEM_1_KEY;

@SpringBootTest
class ApplicationTests {

  @Autowired
  private AppExternalConfiguration appExternalConfiguration;

  @Test
  void contextLoads() {
    Assertions.assertEquals(2, appExternalConfiguration.systemsAuth().size());
    Assertions.assertEquals("a_login", appExternalConfiguration.systemsAuth().get(SYSTEM_1_KEY).login());
    Assertions.assertEquals(134, appExternalConfiguration.listItemConfigurations().getFirst().itemValue());
  }

}
