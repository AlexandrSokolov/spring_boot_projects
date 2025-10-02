
- [Configuration properties injection](#configuration-properties-injection)
- [Injection ways/location](#injection-wayslocation)
- [Field Injection issue](#field-injection-issue)
- [Injection of all beans of the type](#injection-of-all-beans-of-the-type)
- [How it works when no matching candidate beans are available?](#how-it-works-when-no-matching-candidate-beans-are-available)
- [Management of multiple beans of the same type](#management-of-multiple-beans-of-the-same-type)
- [Using Generics as Autowiring Qualifiers](#generics-as-autowiring-qualifiers)
- [Support of other injection-related annotations](#support-of-other-injection-related-annotations)

Official documentation:
- [Annotation-based Container Configuration](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config.html)

### Configuration properties injection

You can inject property:
```java
@Value("${app.test.q1}")
String q1;
```

See:
- [`PropertyInjection`](src/main/java/com/savdev/demo/sb/service/PropertyInjection.java)
- [Using `org.springframework.beans.factory.annotation.Value`](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/value-annotations.html)



### Injection ways/location

- [On constructor](src/main/java/com/savdev/demo/sb/consumer/injection_ways/ConstructorInjection.java)
- [On setter](src/main/java/com/savdev/demo/sb/consumer/injection_ways/SetterInjection.java)
- [On field](src/main/java/com/savdev/demo/sb/consumer/injection_ways/FieldsInjection.java)

### Field Injection issue

ОТКРОВЕННАЯ ЕРЕСЬ!

- [The official Spring documentation doesn’t provide field injection as one of the DI options anymore](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html)
- [But you could find it in other official docs](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired.html)

Issues:
- [Null safety](https://www.baeldung.com/java-spring-field-injection-cons#null-safety)
    Note: Can happen only is instantiated without the Spring context. 
    If it under your control, you never meet anything like this, 
    cause `Autowired` marks injection of field required.
- [Immutablity](https://www.baeldung.com/java-spring-field-injection-cons#immutability)
    Note: By default, Spring beans have a singleton scope. To consider immutability as an issue, you must be joking.
- [Single Responsibility Violation](https://www.baeldung.com/java-spring-field-injection-cons#1-single-responsibility-violation)
    Note: this "violation" is not solved by constructor or setter injection, mention it only for field injection, ridiculous.
- [Circular Dependencies](https://www.baeldung.com/java-spring-field-injection-cons#2-circular-dependencies)
    This is the only valuable reason to avoid fields injection. 
    Was. Since Spring Boot 2.6. version circular dependencies are no longer allowed by default. So it is gone!
- [Testing and mocking](https://www.baeldung.com/java-spring-field-injection-cons#testing).
    Wrong visibility. Make fields not private, but package private. Then you can set it in the tests. 

### Injection of all beans of the type

### How it works when no matching candidate beans are available?

By default, autowiring fails when no matching candidate beans are available for a given injection point. 
In the case of a declared array, collection, or map, at least one matching element is expected.

The default behavior is to treat annotated methods and fields as indicating required dependencies.

You can change this behavior with:
- `@Autowired(required = false)`
- `java.util.Optional`:
    ```java
    public class SimpleMovieLister {
      @Autowired
      public void setMovieFinder(Optional<MovieFinder> movieFinder) {
      }
    }
    ```
- `@Nullable` - of any kind in any package - for example, `javax.annotation.Nullable` from JSR-305:
    ```java
    public class SimpleMovieLister {
      @Autowired
      public void setMovieFinder(@Nullable MovieFinder movieFinder) {
      }
    }
    ```
- 

### Management of multiple beans of the same type

1. If you have multiple implementations, and one primary candidate can be determined, 
    you could use [`org.springframework.context.annotation.Primary`](src/main/java/com/savdev/demo/sb/service/InjectionDemoService1Primary.java):
    ```java
    @Service
    @Primary
    public class InjectionDemoService1Primary implements InjectionDemoApi {}
    ```
   
   Note that the standard `jakarta.annotation.Priority` annotation is not available at the `@Bean` level.
   The other implementations can be received via [injection of all the beans of the type](src/main/java/com/savdev/demo/sb/consumer/injection_ways/AllBeansInjection.java).

    You could use this annotation also on the bean's configuration:
    ```java
    @Configuration
    public class MovieConfiguration {
    
      @Bean
      @Primary
      public MovieCatalog firstMovieCatalog() { ... }
    
      @Bean
      public MovieCatalog secondMovieCatalog() { ... }
    }
    ```
2. Use `@Qualifier` annotation by injection name to get more control over the selection process.
    See examples under `com.savdev.demo.sb.service.multiple` package 
    and [`MultipleBeanTypesInjectionTest`](src/test/java/com/savdev/demo/sb/service/MultipleBeanTypesInjectionTest.java)
3. Use [custom qualifier annotation](src/main/java/com/savdev/demo/sb/consumer/qualifier/Service4WithQualifierCustomAnnotationConsumer.java)
4. Use [implementation class name](src/main/java/com/savdev/demo/sb/consumer/qualifier/Service3WithoutQualifierConsumer.java) 
   for injection.

### Generics as Autowiring Qualifiers

They are supported, see [Using Generics as Autowiring Qualifiers](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/generics-as-qualifiers.html)

### Support of other injection-related annotations

Spring supports:
- [`jakarta.inject.Inject` from JSR-330](src/main/java/com/savdev/demo/sb/consumer/injection_ways/JakartaInjection.java)
- [`jakarta.annotation.Resource` from JSR-250](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/resource.html)