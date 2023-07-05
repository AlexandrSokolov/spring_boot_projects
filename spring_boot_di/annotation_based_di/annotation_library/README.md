### Library with POJOs

The library defines api interfaces, the implementations and different services that inject dependencies via Spring annotations.
Container configuration is provided by the library via [`AnnotationLibraryConfiguration`](src/main/java/com/example/di/ann/lib/AnnotationLibraryConfiguration.java)
The client  of the library imports this `AnnotationLibraryConfiguration` configuration as:
```java
@Configuration
@ComponentScan("com.example.di.ann.app")
@Import(AnnotationLibraryConfiguration.class) //import the library IoC  container configuration
public class AppContainerConfiguration {
}
```

### Dependency to the library is defined in `pom.xml` as:

```xml
    <dependency>
      <groupId>com.example.lib.di.annotation</groupId>
      <artifactId>annotation-di-library</artifactId>
      <version>1.0.0</version>
    </dependency>
```



*** [Configure IoC container of the application to use this library](../annotation_application/README.md#configure-ioc-container-with-plain-java-library)