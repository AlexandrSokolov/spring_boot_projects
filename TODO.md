### add db integration tests from deka-dim project

### spring configuration

https://www.baeldung.com/properties-with-spring
[Guide to @ConfigurationProperties in Spring Boot](https://www.baeldung.com/configuration-properties-in-spring-boot)
https://www.baeldung.com/spring-boot-properties-canonical-form

you can't use @ConfigurationProperties with @Value.
https://stackoverflow.com/questions/78521164/issue-with-configurationproperties-and-value-when-used-together-in-spring

@PropertySource("classpath:application.properties") - works only with usual properties, not with yaml files

https://howtodoinjava.com/spring-boot/properties-with-spring-boot/

### spring di issue

[The BeanDefinitionOverrideException in Spring Boot](https://www.baeldung.com/spring-boot-bean-definition-override-exception)
```text
Invalid bean definition with name 'testBean' defined in ... 
... com.baeldung.beandefinitionoverrideexception.TestConfiguration2 ...
Cannot register bean definition [ ... defined in ... 
... com.baeldung.beandefinitionoverrideexception.TestConfiguration2] for bean 'testBean' ...
There is already [ ... defined in ...
... com.baeldung.beandefinitionoverrideexception.TestConfiguration1] bound.
```

My error:
```text
Caused by: org.springframework.beans.factory.support.BeanDefinitionOverrideException: 

Invalid bean definition with name 'itemRepository' defined in com.brandmaker.cs.deka.dim.repository.ItemRepository defined in @EnableJpaRepositories declared on DbTestContainerInitializer: 

Cannot register bean definition 

[Root bean: class [org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean]; 
scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodNames=null; destroyMethodNames=null; 

defined in com.brandmaker.cs.deka.dim.repository.ItemRepository defined in @EnableJpaRepositories declared on DbTestContainerInitializer] for bean 'itemRepository' since there is already 

[Root bean: class [org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodNames=null; destroyMethodNames=null; defined in com.brandmaker.cs.deka.dim.repository.ItemRepository defined in @EnableJpaRepositories declared on TestJpaConfiguration] bound.ined in @EnableJpaRepositories declared on TestJpaConfiguration] bound.
```

Solution: `@EnableJpaRepositories` and `@EntityScan` are defined both on:
```java
@Configuration
@EnableJpaRepositories
@EntityScan
public class DbTestContainerInitializer  implements ApplicationContextInitializer<ConfigurableApplicationContext> {
```
and:
```java
@Configuration
@EnableJpaRepositories("com.....repository")
@EntityScan("com.....repository.entities")
public class TestJpaConfiguration {}
```
I commented out the annotaitons on DbTestContainerInitializer it fixed the issue

### [Spring Boot Jax Rs Web App](spring_boot_web/jaxrs_resteasy/TODO.md)