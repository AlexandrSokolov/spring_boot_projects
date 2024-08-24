- [Define library dependencies in `pom.xml`](#spring-dependencies-in-pomxml-of-the-library)
- [Explicitly document the library DI configuration](#library-di-configuration)
- [Provided maven dependencies on the library](#library-maven-dependencies)

- [Library's consumer/client](../annotation_application/README.md))

- [Library description](#library-with-pojos)

### Spring dependencies in `pom.xml` of the library

You do not need to configure:
- `spring-boot-starter` for the library they are not needed is defined.
- `spring-boot-maven-plugin`, otherwise you get:
  > Execution repackage of goal org.springframework.boot:spring-boot-maven-plugin:3.3.3:repackage failed: 
  > Unable to find main class

  We do not need the main class for the library

The only `spring-starter` - is `spring-boot-starter-test`

If you use log4j2 dependency on the project, exclude `spring-boot-starter-logging` from `spring-boot-starter-test`:
```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
  
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  </dependencies>
```

[See `pom.xml`](pom.xml) 

### Library DI configuration

Configuration is defined in [`AnnotationLibraryConfiguration`](src/main/java/com/example/di/ann/lib/AnnotationLibraryConfiguration.java):
```java
@Configuration
@ComponentScan("com.example.lib")
public class AnnotationLibraryConfiguration {}
```

The consumer of the library must include in its configuration:
```java
@Configuration
@ComponentScan("com.example.consumer")
@Import(AnnotationLibraryConfiguration.class) //import the library IoC  container configuration
public class AppContainerConfiguration {
}
```

### Library maven dependencies:
```xml
    <dependency>
      <groupId>com.example.lib.di.annotation</groupId>
      <artifactId>annotation-di-library</artifactId>
      <version>1.0.0</version>
    </dependency>
```

### Library with POJOs

The library defines
- api interfaces, 
- the implementations and 
- different services that inject dependencies via Spring annotations
