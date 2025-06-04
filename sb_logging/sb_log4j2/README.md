Spring Boot Web App based on Jakarta/Jax Rs (implementation from RESTEasy) with **log4j2** as logging provider

### Run the app, send request, check the logs

Start the project as:

```bash
$ mvn spring-boot:run
```

In the log you'll see:
> 2025-05-28T15:17:33.845+02:00  INFO 596633 --- [           main] c.e.s.l.SbLog4j2Application              : Application has been started. Custom message

Send rest requests via cmd to test the app:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/items/1
```

In the log you'll see:
> 2025-05-28T15:18:40.095+02:00  INFO 596633 --- [nio-8080-exec-1] c.e.s.l.r.s.ItemRestService              : rest call - get item by id = 1

### To switch to the log4j2 solution from Spring:

1. Exclude `spring-boot-starter-logging` and add `spring-boot-starter-log4j2` dependency.
2. Enforce the version of JCL - `commons-logging` that supports `log4j2`
```xml
  <dependencyManagement>
   <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.3.5</version>
   </dependency>
  </dependencyManagement>

  <dependencies>
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
  </dependencies>
```

### Log4j2 dependencies management

The It includes dependencies on:
- `log4j-api` - logging API (you still can use `slf4j` api)
- `log4j-core` - logging implementation
- `log4j-slf4j2-impl` - to translate SLF4J calls to Log4j API (SLF4J-to-Log4j bridge)
- `log4j-jul` - to translate JUL (Java Logging) calls to Log4j API (JUL-to-Log4j bridge)
```text
[INFO] +- org.springframework.boot:spring-boot-starter-log4j2:jar:3.5.0:compile
[INFO] |  +- org.apache.logging.log4j:log4j-slf4j2-impl:jar:2.24.3:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  +- org.apache.logging.log4j:log4j-core:jar:2.24.3:compile
[INFO] |  \- org.apache.logging.log4j:log4j-jul:jar:2.24.3:compile
```

Notes:
1. In order to activate the bridge from JUL to Log4j API, you also need to add to the JVM parameters:
    ```bash
    -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager
    ```
2. Installing JCL-to-Log4j bridge
   Since version 1.3.0 Apache Commons Logging natively supports Log4j API.

   You can **enforce the version of a transitive dependency** using the dependency management mechanism
   appropriate to your build tool:
   ```xml
   <dependencyManagement>
      <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.3.5</version>
      </dependency>
    </dependencyManagement>
   ```
3. `org.jboss.logging:jboss-logging` JBoss Logging - is shipped with an integrated bridge to Log4j API and requires 
   no additional steps to configure.

### Java code to use log4j2 API:

```java
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

class L {
  //getLogger() associates the returned Logger with the enclosing class, that is, `L` in this example.
  private static final Logger logger = LogManager.getLogger(); 
}
```
