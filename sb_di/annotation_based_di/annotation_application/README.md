### TODO (check and update both library and the consumer:

It looks as it is not required to use `@Import(AnnotationLibraryConfiguration.class)`
It is enouph just to scan packages of the library:
```java
@Configuration
@ComponentScan("com.brandmaker.cs.commons.mp")
public class BmDiConfiguration {
}
```

### Requirement: 

The application uses the library. The library is not part of this Maven project.
It is independent project that must be build and installed to the local maven repository.
It is also expected that library is published to the Nexus, to make the library available for downloading.

To build this application, you must run `mvn clean install` from the `../annotation_library` folder first.

#### Annotation-based configuration 

We mark the application package to be scanned via `@ComponentScan("com.example.di.ann.app")` 
and import the configuration provided by library via `@Import(AnnotationLibraryConfiguration.class)`

See [`AppContainerConfiguration`](src/main/java/com/example/di/ann/app/AppContainerConfiguration.java)
```java
@Configuration
@ComponentScan("com.example.di.ann.app")
@Import(AnnotationLibraryConfiguration.class) //import the library IoC  container configuration
public class AppContainerConfiguration {
}
```

#### Add the dependency to a library:
```xml
    <dependency>
      <groupId>com.example.lib.di.annotation</groupId>
      <artifactId>annotation-di-library</artifactId>
      <version>1.0.0</version>
    </dependency>
```

#### Send request (server is on port 9090, locally it is exposed to 8080):

```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest
````

