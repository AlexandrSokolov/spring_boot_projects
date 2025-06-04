package com.example.sb.slf4j.logback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbSlf4JLogbackApplication {

  //same as LogManager.getLogger(SbLog4j2Application.class);
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    SpringApplication.run(SbSlf4JLogbackApplication.class, args);
    logger.info("Application has been started. Custom message");
  }
}
