Spring Boot Web App based on Jakarta/Jax Rs (implementation from RESTEasy) 
with a default `log4j` as logging api and `logback` as a logging provider.

Note:
**You can use both `slf4j` and `log4j2` api in the applications due to `log4j-to-slf4j` dependencies in the project.**

- [Run the app, send request, check the logs](#run-the-app-send-request-check-the-logs)
- [Configure `slf4j` and `logback`](#configure-slf4j-and-logback)
- [Slf4J dependencies management](#slf4j-dependencies-management)
- [Java code to use slf4j API](#java-code-to-use-slf4j-api)
- [Java code to use log4j2 API](#java-code-to-use-slf4j-api)

### Run the app, send request, check the logs

Start the project as:

```bash
mvn spring-boot:run
```

In the log you'll see:
> 2025-05-28T15:17:33.845+02:00  INFO 596633 --- [           main] c.e.s.s.l.SbSlf4JLogbackApplication              : Application has been started. Custom message

Send rest requests via cmd to test the app:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/items/1
```

In the log you'll see:
> 2025-05-28T15:18:40.095+02:00  INFO 596633 --- [nio-8080-exec-1] c.e.s.s.l.rest.service.ItemRestService           : rest call - get item by id = 1

### Configure `slf4j` and `logback`

Spring comes with `slf4j` and `logback` via `spring-boot-starter-logging`.

You don't need to apply any additional steps.

### Slf4J dependencies management

The It includes dependencies on:
- `org.slf4j:slf4j-api` - logging API
- `logback-classic` - logging implementation
- `log4j-to-slf4j` - to translate log4j calls to SLF4J API (Log4j-to-SLF4J bridge)
- `jul-to-slf4j` - to translate JUL (Java Logging) calls to Log4j API (JUL-to-Log4j bridge)
```text
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:3.5.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.5.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.5.0:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
```

Note: you can use both `slf4j` and `log4j2` api in the applications due to `log4j-to-slf4j` dependencies in the project. 

### Java code to use slf4j API

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class L {
  private static final Logger logger = LoggerFactory.getLogger(L.class);
}
```

### Java code to use log4j2 API

You still can use it thanks to `log4j-to-slf4j` dependency

```java
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

class L {
  //getLogger() associates the returned Logger with the enclosing class, that is, `L` in this example.
  private static final Logger logger = LogManager.getLogger(); 
}
```
