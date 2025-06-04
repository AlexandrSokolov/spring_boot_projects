- [Set logging level at runtime](#set-logging-level-at-runtime)
- [Set logging level during application startup](#set-logging-level-during-application-startup)
- [Built-time logging configuration](#built-time-logging-configuration)
- [Logging API providers](#logging-api-providers)
- [Logging implementation providers](#logging-implementation-providers)
- [Outdated logging solutions](#outdated-logging-solutions)

### Set logging level at runtime

1. via Spring Boot Actuator

    Add the Spring Boot Actuator Maven dependency
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```
    Enable the `/loggers` endpoint in our application.properties file:
    ```yaml
    management:
      endpoints:
        web:
          exposure:
            include:
              - loggers
      endpoint:
        loggers:
          access: read_only
    ```
    [See `Actuator Endpoints` for more information](https://docs.spring.io/spring-boot/reference/actuator/endpoints.html)
    [change log for the changed properties](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.4-Configuration-Changelog)
    
    View current loggers:
    ```bash
    curl -i -X GET -w "\n" http://localhost:8080/actuator/loggers
    ```
    View a specific logger:
    ```bash
    curl -i -X GET -w "\n" http://localhost:8080/actuator/loggers/com.example.sb.log4j2
    ```
    Changing Log Levels at Runtime;
    ```bash
    curl -i -X POST -w "\n" -H 'Content-Type: application/json' -d '{"configuredLevel": "DEBUG"}' http://localhost:8080/actuator/loggers/com.example.sb.log4j2 
    ```
    Change it back:
    ```bash
    curl -i -X POST -w "\n" -H 'Content-Type: application/json' -d '{"configuredLevel": "INFO"}' http://localhost:8080/actuator/loggers/com.example.sb.log4j2 
    ```

2. Using `LoggingSystem`

   `LoggingSystem` is a Spring component that provides logging functionality in Spring Boot.
   Create a simple endpoint and call `setLogLevel()` to change the logging level of our application at runtime.

   See [`DebugLoggingConfigRestService`](sb_log4j2/src/main/java/com/example/sb/log4j2/rest/service/DebugLoggingConfigRestService.java):
   ```java
   public class DebugLoggingConfigRestService implements DebugLoggingConfigApi {
     @Override
     public void enableDebugLevel(String packageName) {
       LoggingSystem.get(DebugLoggingConfigRestService.class.getClassLoader())
         .setLogLevel(packageName, LogLevel.DEBUG);
     }
   }
   ```   
   Set debug mode for `com.example.sb.log4j2` package:
   ```bash
   curl -i -X POST -w "\n" -H 'Content-Type: application/json' http://localhost:8080/rest/logging/config/debug/com.example.sb.log4j2
   ```
   
   Set info mode for `com.example.sb.log4j2`:
   ```bash
   curl -i -X POST -w "\n" -H 'Content-Type: application/json' http://localhost:8080/rest/logging/config/info/com.example.sb.log4j2
   ```

### Set logging level during application startup

We can use a `logging.level` property, responsible for setting a single package (and only package).
We can use a `spring.application.json` property, for more complicated configurations, including java classes.

1. Via environment variables

   ```bash
   export LOGGING_LEVEL_COM_EXAMPLE_SB_LOG4J2=DEBUG && java -jar target/sb-log4j2-1.0.0.jar
   ```
   or more for more complicated configurations:
   ```bash
   export SPRING_APPLICATION_JSON='{"logging.level.com.example.sb.log4j2":"debug"}' && java -jar target/sb-log4j2-1.0.0.jar
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   export LOGGING_LEVEL_COM_EXAMPLE_SB_LOG4J2=DEBUG && mvn spring-boot:run
   ```
   or:
   ```bash
   export SPRING_APPLICATION_JSON='{"logging.level.com.example.sb.log4j2":"debug"}' && mvn spring-boot:run
   ```

2. Via system properties

   ```bash
   java -Dlogging.level.com.example.sb.log4j2=debug -jar target/sb-log4j2-1.0.0.jar
   ```
   or more for more complicated configurations:
   ```bash
   java -Dspring.application.json='{"logging.level.com.example.sb.log4j2":"debug"}' -jar target/sb-log4j2-1.0.0.jar
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogging.level.com.example.sb.log4j2=debug"
   ```
   or (not recommended, format is complicated):
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.application.json='{\"logging.level.com.example.sb.log4j2\":\"debug\"}'"
   ```

3. Via command line arguments:

   ```bash
   java -jar target/sb-log4j2-1.0.0.jar --logging.level.com.example.sb.log4j2=debug
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--logging.level.com.example.sb.log4j2=debug"
   ```
   or:
   ```bash
   java -jar target/sb-log4j2-1.0.0.jar --spring.application.json='{"logging.level.com.example.sb.log4j2":"debug"}'
   ```
   or (not recommended, format is complicated):
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--spring.application.json='{\"logging.level.com.example.sb.log4j2\":\"debug\"}'"
   ```

### Built-time logging configuration

The configuration file inside the package:
```yaml
logging:
  level:
    com:
      example:
        sb:
          log4j2: info
    org:
      springframework: info
      jboss:
        resteasy: info
```

### Logging API providers

- [`slf4j` (supports also `log4j` api)](sb_slf4j_logback/README.md)
- [`log4j2` with `log4j2` as implementation](sb_log4j2/README.md)
- [`log4j2` with `logback` as implementation](sb_log4j2_api_logback/README.md)

### Logging implementation providers

1. `Logback` - is a default logging implementation provider in Spring Boot
2. `Log4j2`.

Note: [you can use `log4j2` as api only with `logback` as implementation.](sb_log4j2_api_logback/README.md)

### Outdated logging solutions

Java Util Logging (JUL)
```java
import java.util.logging.Logger;

class L {
  private static final Logger logger = Logger.getLogger(L.class.getName());
}
```
Don't use it in your applications. JUL - is a legacy logging framework.

Our third-party dependencies might use JUL despite our preference. As a result, 
we must always configure to route all incoming JUL records into our chosen logging API provider.