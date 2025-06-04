
This DEMO project is based on [`jakarta_resteasy` project](../sb_web/jakarta_resteasy).

Start the project:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.config.import=file:./src/test/resources/app.external.config.yaml
```

Get the configuration via request. Send rest requests via cmd to test the app:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/config
```

## External configuration issues and solutions:
* [Pass the configuration properties](#pass-the-configuration-properties)
* [Define configuration types structure](#define-configuration-types-structure)
* [Create the configuration file](#create-appexternalconfigyaml-configuration-file)
* [Pass the configuration file for tests](#import-the-configuration-file-into-tests)
* [Pass the configuration, when run via maven](#how-to-pass-the-path-to-the-configuration-file-into-the-web-application-when-you-run-it-via-mvn-spring-bootrun)
* [Pass the configuration, when run via docker compose](#how-to-pass-the-path-to-the-configuration-file-into-the-web-application-when-you-run-it-via-docker-composition)

### Pass the configuration properties

1. Via environment variables
   ```bash
   export LOGGING_LEVEL_COM_EXAMPLE_SB_LOG4J2=DEBUG && java -jar target/sb-log4j2-1.0.0.jar
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   export LOGGING_LEVEL_COM_EXAMPLE_SB_LOG4J2=DEBUG && mvn spring-boot:run
   ```
2. Via system properties
   ```bash
   java -Dlogging.level.com.example.sb.log4j2=debug -jar target/sb-log4j2-1.0.0.jar
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogging.level.com.example.sb.log4j2=debug"
   ```
3. Via command line arguments:
   ```bash
   java -jar target/sb-log4j2-1.0.0.jar --logging.level.com.example.sb.log4j2=debug
   ```
   or when you run via `mvn spring-boot:run`:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.arguments="--logging.level.com.example.sb.log4j2=debug"
   ```
   
### Define configuration types structure

You might use either java records or java POJOs for such types.

1. Define the root configuration class with `@ConfigurationProperties("app")` annotation.
    
    [See `AppExternalConfiguration` config](src/main/java/com/example/external/config/AppExternalConfiguration.java)

    `app` used in `@ConfigurationProperties` - is the root path in the yaml configuration:
    ```yaml
    app:
      field1: value1
      field2: value2
    ```

2. Enable `AppExternalConfiguration` the configuration via `@EnableConfigurationProperties(AppExternalConfiguration.class)`

    [See `AppDiConfiguration` config](src/main/java/com/example/external/config/AppDiConfiguration.java)

3. Add the configuration types: `AuthConfiguration`, `ListItemConfiguration`, `NotificationConfiguration` contained by `AppExternalConfiguration`

### [Create `app.external.config.yaml` configuration file](src/test/resources/app.external.config.yaml)

Notes:

1. For java fields with camel case names, like `listItemConfigurations` we use dash-case naming style for yaml: `list-item-configurations`
    TODO: if we want to overwrite the names, how can we do that? Looks as it is a challenge for external yaml files.
2. If field name (`system1`, `system2`) might be used as a key in the configuration, for instance:
    ```yaml
      systems-auth:
        system1:
          host: a.com
          login: a_login
          password: b_pass
        system2:
          host: b.com
          login: b_login
          password: b_pass
    ```
    Then in Java, you might define it as:
    ```java
    @ConfigurationProperties()
    public record AppExternalConfiguration (
      //system name -> auth mappings
      Map<String, AuthConfiguration> systemsAuth
    ){}
   
    public record AuthConfiguration (
      String login,
      String password,
      String host
    ) {}
    ```
3. 

### Import the configuration file into tests

Add in test [`application.yaml`](src/test/resources/application.yaml) the class path to the file:
```yaml
spring:
  config:
    import: classpath:app.external.config.yaml
```

### How to pass the path to the configuration file into the web application when you run it via `mvn spring-boot:run`

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.config.import=file:./src/test/resources/app.external.config.yaml
```
or:
```bash
export SPRING_CONFIG_IMPORT=file:./src/test/resources/app.external.config.yaml && mvn spring-boot:run
```

### How to pass the path to the configuration file into the web application when you run it via docker composition

To run the docker composition for the first time:
```bash
 mvn clean package && docker compose up
```

To rebuild and rerun:
```bash
./clearAndStart.sh
```

The key parts in the [`docker-compose.yaml` file:](docker-compose.yaml)
```yaml
services:
  sb-external-config-app:
    volumes:
      - ./src/test/resources/app.external.config.yaml:/etc/demo/app.external.config.yaml
    environment:
      - SPRING_CONFIG_IMPORT=file:/etc/demo/app.external.config.yaml
```

The file must be mounted to the certain path on the docker container, and that path must be passed via `SPRING_CONFIG_IMPORT`