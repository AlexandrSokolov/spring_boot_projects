### Requirement: 

The application uses the library. The library is not part of this Maven project.
It is independent project that must be build and installed to the local maven repository.
It is also expected that library is published to the Nexus, to make the library available for downloading.

To build this application, you must run `mvn clean install` from the `../library` folder first.

#### Xml configuration file naming and location

Xml configuration file define beans, so it makes sense to name it `beans.xml`.

CDI uses that `beans.xml` as well and stores it in jar files as: `src/main/resources/META-INF/beans.xml`

To highlight `beans.xml` Spring DI XML is not CDI, we store our configuration as: `src/main/resources/beans.xml`

#### Import xml configuration file

```@ImportResource("classpath:beans.xml")```

While you can apply that annotation on the app class:
```java
@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class Application ...
```

Preferrable option is to declare a separate configuration class:
```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:beans.xml")
public class XmlContainerConfiguration {
}
```

#### Configure rest controller 

Note: `AppController` is not configured in `beans.xml` as it is annotation-based bean.

All the services are injected with `@Autowired` in that controller.

#### Configure IoC container with plain java library

1. Add the dependency to a library:
```xml
    <dependency>
      <groupId>com.example.lib.di.xml</groupId>
      <artifactId>xml-di-library</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
```
2. Define beans from the library, to make them injectable in the application:

See [`library-beans.xml`](src/main/resources/library-beans.xml)

Note: to make it easier, the library provides its own version of `library-beans.xml` file, 
but you must edit it to make bean id/names unique.

Add the `library-beans.xml` configuration:
```java
@Configuration
@ImportResource( {"classpath:beans.xml", "classpath:library-beans.xml"})
public class XmlContainerConfiguration {
}
```

Now you can inject services from the library as beans.

#### Send request (server is on port 9090, locally it is exposed to 8080):

```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest
````

