
- [Spring Bean](#spring-bean)
- [Spring Beans registration](#spring-beans-registration)
- [Searching for Spring beans](#searching-for-spring-beans)
- [Using `@ComponentScan` together with `@SpringBootApplication`](#using-componentscan-together-with-springbootapplication)
- [Adding specific configuration beans into the context](#adding-specific-configuration-beans-into-spring-context)
- [Best Practices for Component Scanning](#best-practices-for-component-scanning)
- [Overriding Spring Beans in Integration Tests](#overriding-spring-beans-in-integration-tests)
- [Spring context tuning in tests]()


Subprojects:
- [Injection in Spring Boot](injection_in_depth/README.md)
- [Spring Bean Scopes](scopes/README.md)

### Spring Bean

todo

### Spring Beans registration

To register Spring bean:
1. Spring autoconfiguration must be enabled
2. [Spring must know where to search for beans](#searching-for-spring-beans)
3. [The bean must be marked accordingly (@Bean, @Component, @Service, @Controller)](#spring-bean)

`@EnableAutoConfiguration` - enables autoconfiguration. This annotation is part of `@SpringBootApplication`:
```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public @interface SpringBootApplication {}
```
As a result by default autoconfiguration is enabled.

### Searching for Spring beans

With `@ComponentScan` annotation we can specify the packages that we want to be scanned.
The class annotated with this annotation is also marked with `@Configuration` annotation:
```java
@Configuration
@ComponentScan
public class SpringComponentScanApp {}
```

`@ComponentScan` without arguments tells Spring to scan the current package and all of its sub-packages.

`@ComponentScan` is contained by `@SpringBootApplication`. 
As a result by default (with `@SpringBootApplication` enabled) Spring scans all the sub-packages of the application.

You may want to customize the component scanning behavior to:
- include beans from packages outside the default base package or 
- exclude specific beans or packages from being scanned. 
In this case `@ComponentScan` can be used explicitly.

### Using `@ComponentScan` together with `@SpringBootApplication`

`@SpringBootApplication` contains `@ComponentScan` and marks all the sub-packages searchable for scans.
If you set both `@SpringBootApplication` and `@ComponentScan` on the app class, 
you overwrite this default scan logic. 
And the application packages are not scanned anymore, until they are not mentioned in `@ComponentScan`.

```java
package a.b.c;

@SpringBootApplication
@ComponentScan("a.b.d")
public class MyApp {}
```
In the example above, `a.b.c` - application package of `MyApp` and its subpackages are not scanned.

Possible solutions:
1. Include `a.b.c` explicitly:
    ```java
    package a.b.c;
    
    @SpringBootApplication
    @ComponentScan( {"a.b.c", "a.b.d" })
    public class MyApp {}
    ```
2. Or a better approach is to extract custom scanning configuration into a separate class:
    ```java
    package a.b.c;
    
    @SpringBootApplication
    public class MyApp {}
    ```
    and the custom scan package configuration:
    ```java
    package a.b.c.config;
    
    @ComponentScan("a.b.d")
    public class MyDiCustomConfig {}
    ```
3. Extend the current application context with a configuration from external package using `@Import`:
    ```java
    package a.b.c;
    
    @SpringBootApplication
    @Import({ExternalPackageDiCustomConfig.class})
    public class MyApp {}
    ```
   and the custom configuration from external package:
    ```java
    package a.e.f;
    
    @Configuration
    public class ExternalPackageDiCustomConfig {}
    ```

### Adding specific configuration beans into Spring context

As an alternative to `@ComponentScan` you could use `@Import` to explicitly import specific configurations 
or beans into the application context.


### Best Practices for Component Scanning

- **Keep the Default Scanning Scope Narrow**: 
  Try to keep the base package of your application narrowly focused to avoid scanning too many unnecessary classes. 
  This can help improve the startup time and performance of your application.
- **Use Custom Scanning When Necessary**: 
  Overusing @ComponentScan may lead to confusion or unexpected results if not properly managed.
- Avoid Scanning for Non-Spring Components

### Overriding Spring Beans in Integration Tests

```java
public interface Service {
  String helloWorld();
}
public class ServiceImpl implements Service {
  public String helloWorld() {
    return "hello world";
  }
}
@Configuration
public class Config {
  @Bean
  public Service helloWorld() {
    return new ServiceImpl();
  }
}
```

#### Using @Primary

```java
@TestConfiguration
public class PrimaryTestConfig {
  @Primary
  @Bean
  public Service helloWorld() {
    return new PrimaryServiceStub();
  }
}
```
and test itself:
```java
@SpringBootTest(classes = { ProfileTestConfig.class })
class ProfileIntegrationTest { }
```

#### Using @MockBean and @MockitoSpyBean

TODO

#### Using @TestBean

Requires both:
1. `@TestBean` annotation applied and 
2. `customServiceTestOverride()` static factory:
```java
@Configuration
class ProdConfiguration {

  @Bean
  MyService customService() {
    return new ProdServiceImpl();
  }
}

@SpringJUnitConfig
class MyServiceIntegrationTests {

  @TestBean
  MyService customService;

  static MyService customServiceTestOverride() {
    return new SimplifiedServiceImpl();
  }

  @Test
  void test(ApplicationContext context) {
    assertThat(context.getBean("customService")
      .isSameAs(this.customService)
      .isInstanceOf(SimplifiedServiceImpl.class);
    //...
  }
}
```

#### Using @Profile

This solution requires to change source code by adding `@Profile`
and makes implementation more complicated:
```java
@Configuration
@Profile("prod")
public class ProfileConfig {
  @Bean
  public Service helloWorld() {
    return new ServiceImpl();
  }
}
```
Test configuration:
```java

@TestConfiguration
public class ProfileTestConfig {
  @Bean
  @Profile("stub")
  public Service helloWorld() {
    return new ProfileServiceStub();
  }
}
```
and test itself:
```java
@SpringBootTest(classes = { ProfileTestConfig.class })
@ActiveProfiles("stub")
class ProfileIntegrationTest {
  
}
```
#### Using @ConditionalOnProperty

This solution requires to change the source code by adding `@ConditionalOnProperty` 
and makes implementation more complicated.

### Spring context tuning in tests

`@SpringBootTest` - loads all the beans, configured for scans for the application into the application context.
This context can be very big, and for some tests, it's not required.
You might want to fine-tune the beans loaded.

#### [with `@ContextConfiguration`](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-contextconfiguration.html)

This annotation can be applied to a test class to configure metadata 
that is used to determine how to load and configure Spring context.



TODO - to confirm:
With `@ContextConfiguration` annotation you disable all the configurations for the application.
It just says what beans would you like to use in Spring context.
You explicitly set the context for loading for the test.

`@ContextConfiguration(classes = SomeConfiguration.class)` means to use only this configuration.
`@Import(SomeConfiguration.class)` adds the configuration class to existing context.

#### with `@TestConfiguration`

Can be used to define **additional beans** or override existing beans.

You have to import such configuration explicitly with `@Import`:
```java
@TestConfiguration
public class WebClientTestConfiguration {}

@SpringBootTest
@Import(WebClientTestConfiguration.class)
class TestConfigurationExampleAppTests {}
```

#### with `@Import` or with `@ComponentScan` annotations
