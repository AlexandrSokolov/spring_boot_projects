### Description

* [Add Jakarta API with Resteasy](#add-jakarta-api-with-resteasy)
* [Add Actuator (health checks)](#add-actuator-health-checks)
* [Run the app and send request for testing](#run-the-app-and-send-request-for-testing)

### Run the app and send request for testing

Start project as:

```bash
$ mvn spring-boot:run
```

Send rest requests via cmd to test the app:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/items/1
curl -i -X GET -w "\n" http://localhost:8080/rest/items/echo/someMessage
curl -i -X GET -w "\n" http://localhost:8080/rest/items/items
curl -i -X PUT -w "\n" -H 'Content-Type: text/plain' -d 'new item' http://localhost:8080/rest/items
```

Trigger validation:
```bash
curl -i -X PUT -w "\n" -H 'Content-Type: text/plain' -d '' http://localhost:8080/rest/items
```

Trigger filter (via `ping` custom header):
```bash
curl -i -X GET -H "ping: valueIgnored" -w "\n" http://localhost:8080/rest/items/1
```

Actuator calls:
```bash
curl -i -X GET -w "\n" http://localhost:8080/actuator/health

```

### Add Jakarta API with Resteasy

In `pom.xml`:

```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- https://mvnrepository.com/artifact/jakarta.ws.rs/jakarta.ws.rs-api -->
    <dependency>
      <groupId>jakarta.ws.rs</groupId>
      <artifactId>jakarta.ws.rs-api</artifactId>
      <version>${jakarta.ws.rs-api.version}</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.jboss.resteasy/resteasy-servlet-spring-boot-starter -->
    <dependency>
      <groupId>org.jboss.resteasy</groupId>
      <artifactId>resteasy-servlet-spring-boot-starter</artifactId>
      <version>${resteasy-servlet-spring-boot-starter.version}</version>
      <scope>runtime</scope>
    </dependency>
  </dependencies>
```

Add rest configurations:
* [Rest app config](src/main/java/com/example/jakarta_resteasy/rest/config/JakartaWsConfiguration.java)
* Register `JakartaWsConfiguration` rest app config file in `application.yaml` as:
  ```yaml
  resteasy:
    jaxrs:
      app:
        registration: property
        classes: com.example.jakarta_resteasy.rest.config.JakartaWsConfiguration
  ```
* [Custom jackson mapper](src/main/java/com/example/jakarta_resteasy/rest/config/CustomJacksonMapperProvider.java)
* [Exception mapper](src/main/java/com/example/jakarta_resteasy/rest/config/NotFoundExceptionMapper.java)
* [Validation exception mapper](src/main/java/com/example/jakarta_resteasy/rest/config/ViolationValidationExceptionMapper.java)

Add rest api module:
* [DTO](src/main/java/com/example/jakarta_resteasy/api/Item.java)
* [Rest api](src/main/java/com/example/jakarta_resteasy/api/ItemRestApi.java)

Implement rest service:
* [Rest service](src/main/java/com/example/jakarta_resteasy/rest/service/ItemRestService.java)

### Add Actuator (health checks)

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
      <scope>runtime</scope>
    </dependency>
```

Actuator calls:
```bash
curl -i -X GET -w "\n" http://localhost:8080/actuator/health

```
