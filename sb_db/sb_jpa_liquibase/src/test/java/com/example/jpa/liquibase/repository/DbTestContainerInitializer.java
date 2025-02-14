package com.example.jpa.liquibase.repository;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;


@TestConfiguration //Not `@Configuration` or the container will be loaded for all tests
public class DbTestContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Container
  public static MySQLContainer<?> mysql = new MySQLContainer<>(
    DockerImageName
      .parse(MySQLContainer.NAME)
      .withTag("8.0.33"))
    .withDatabaseName("items-test-db")
    .withReuse(true);

    static {
      mysql.start();
    }

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    TestPropertyValues.of(
        "spring.datasource.url=" + mysql.getJdbcUrl(),
        "spring.datasource.username=" + mysql.getUsername(),
        "spring.datasource.password=" + mysql.getPassword(),
        "spring.datasource.driver-class-name=" + mysql.getDriverClassName())
      .applyTo(applicationContext.getEnvironment());
  }
}
