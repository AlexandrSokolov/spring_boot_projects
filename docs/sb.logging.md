
### Adding `log4j2` api support

1. Exclude `spring-boot-starter-logging` from `spring-boot-starter-web`
2. Add `spring-boot-starter-log4j2` dependency

  ```xml
  <project>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
  </project>
  ```

3. Create logger as:
  ```java
  import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;
  
  private static final Logger logger = LogManager.getLogger(MyClass.class.getName());
  ```