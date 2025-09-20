Spring Boot Web App based on Jakarta/Jax Rs (implementation from RESTEasy)

### Documentation

* [RESTEasy Spring Boot Starter](https://github.com/resteasy/resteasy-spring-boot)

### Description

* [Add Jakarta API with Resteasy Project Depenencies](#jakarta-api-with-resteasy-project-dependencies)
* [Add Spring Boot Starter with Jakarta Resteasy App](#spring-boot-starter-with-jakarta-resteasy-app)
* [Add Optional Jakarta Configuration](#optional-jakarta-configuration)
* [Add Rest Api and Service](#rest-api-and-service)
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
curl -i -X GET -w "\n" http://localhost:8080/rest/items
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

### [Jakarta API with Resteasy project dependencies](pom.xml)

### Spring Boot Starter with Jakarta Resteasy App

* [Add Spring Boot Starter](src/main/java/com/example/jakarta_resteasy/Application.java)
* [Add Spring Boot Test](src/test/java/com/example/jakarta_resteasy/JakartaResteasyApplicationTests.java)
* [Add Rest app config](src/main/java/com/example/jakarta_resteasy/rest/config/JakartaWsConfiguration.java)
* Register `JakartaWsConfiguration` (update package path!) rest app config file in `application.yaml` as:
  ```yaml
  resteasy:
    jaxrs:
      app:
        registration: property
        classes: config.rest.com.example.sb.log4j2.JakartaWsConfiguration
  ```

### Optional Jakarta Configuration

* [Custom jackson mapper](src/main/java/com/example/jakarta_resteasy/rest/config/CustomJacksonMapperProvider.java)
* [Exception mapper](src/main/java/com/example/jakarta_resteasy/rest/config/NotFoundExceptionMapper.java)
* [Validation exception mapper](src/main/java/com/example/jakarta_resteasy/rest/config/ViolationValidationExceptionMapper.java)
* [Custom header response filter](src/main/java/com/example/jakarta_resteasy/rest/config/PingPongHeaderContainerResponseFilter.java)

### Rest Api and Service

Add rest api module:
* [DTO](src/main/java/com/example/jakarta_resteasy/api/Item.java)
* [Rest api](src/main/java/com/example/jakarta_resteasy/api/ItemRestApi.java)

Implement rest service:
* [Rest service](src/main/java/com/example/jakarta_resteasy/rest/service/ItemRestService.java)

### Actuator (health checks)

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
