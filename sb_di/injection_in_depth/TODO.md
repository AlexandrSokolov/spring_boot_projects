
### bean vs component (service)

### constructor injection without `@Autowired`, why it works

### package scan how @SpringBootApplication affects it

By Default: When you annotate your main application class with @SpringBootApplication, 
it implicitly enables component scanning. 
The scanning occurs from the package where your main application class is located and all its sub-packages.

Default Behavior: Spring Boot will scan the base package 
(the package where the main class annotated with @SpringBootApplication resides) 
and all its sub-packages for Spring components.


Customizing Component Scanning with @ComponentScan
Sometimes, you may want to customize the component scanning behavior 
to include beans from packages outside of the default base package or 
exclude specific beans or packages from being scanned. 
You can achieve this using the @ComponentScan annotation

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.app.services", "com.example.common"})
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

Spring Boot will scan both the com.example.app.services and com.example.common packages for Spring components in addition to the default package.


@SpringBootTest will bootstrap the full application context, 
which means you can autowire any bean that’s picked up by component scanning into our test. 
You might want to avoid bootstrapping the real application context but use a special test configuration. 
You can achieve this with @TestConfiguration annotation.

### Best Practices for Component Scanning

Keep the Default Scanning Scope Narrow: 
Try to keep the base package of your application narrowly focused to avoid scanning too many unnecessary classes. 
This can help improve the startup time and performance of your application.

Use Custom Scanning When Necessary: 
Only use @ComponentScan to include additional packages or exclude specific classes 
when you have a valid reason to deviate from the default scanning behavior. 
Overusing @ComponentScan may lead to confusion or unexpected results if not properly managed.

Avoid Scanning for Non-Spring Components: Make sure that the packages you scan contain only Spring components. 
Scanning packages that do not contain Spring-managed components can lead to unnecessary processing or even conflicts.