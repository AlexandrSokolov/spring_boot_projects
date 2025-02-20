### Description

This project extends functionalities of:
* [Spring Boot Web App based on Jakarta](../../sb_web/jakarta_resteasy/README.md)
* [todo spring docker with docker compose](todo)

### Documentation

* [Database Initialization - Liquibase on Startup](https://docs.spring.io/spring-boot/how-to/data-initialization.html#howto.data-initialization.migration-tool.liquibase)
* [Using Liquibase with Spring Boot](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/)
* [Running Spring Boot with PostgreSQL in Docker Compose](https://www.baeldung.com/spring-boot-postgresql-docker)
* [Guide to JPA’s persistence.xml](https://thorben-janssen.com/jpa-persistence-xml/)
* [JPA and Hibernate query hints](https://thorben-janssen.com/11-jpa-hibernate-query-hints-every-developer-know/)

### Implementation details:
* [Pass db-connection data to the application.](#pass-db-connection-data-to-the-application-full-picture)
* [TODO list to pass db configuration.](#pass-db-connection-data-to-the-application-todo-list)
* [Running application](#running-application)
* [Test functionality](#to-test-functionality)
* [Steps to integrate Spring Boot with Spring Data Jpa and Liquibase](#steps-to-integrate-spring-boot-with-spring-data-jpa-and-liquibase)
* [TODO Unit and integration tests](#unit-and-integration-tests)

### Pass db-connection data to the application. Full picture.

Based on the way how the application gets started we have to keep in mind different things.

The app gets started when we run it via:
- Spring Boot tests execution, for instance when we build the project with `mvn clean package`
- the maven plugin: `mvn spring-boot:run`
- docker composition with an external database docker container or an external db service.

1. Pass db connection to Spring Boot unit tests. `hsqldb` in memory is used.

   When we build application via `mvn clean package`, the `ApplicationTests.java` and other unit tests get triggered.

   `hsqldb` db driver configured with `test` scope in the `pom.xml` is loaded.

   [The `application.yaml` from the test resources](src/test/resources/application.yaml) is used. 
   This test file **can** contain `hsqldb` db settings. In our case it does not contain any db-connection-settings.
   But with the db configuration in memory, the right db connection is used based on the dependencies. 
   You don't need to set anything explicitly.
  
   
2. Pass db connection and a db driver when you run the app via `mvn spring-boot:run` Maven plugin

    By default, if you run `mvn spring-boot:run`. You'll get an exception:
    > Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

    [The `application.yaml` from the main resources](src/main/resources/application.yaml) is used.
    The file does not contain any db settings.
    The settings are passed by the ITO team, are not known in advance and get changed regularly.

    If we want to use `hsqldb` memory db, when start via maven, we should:

    - configure  `spring-boot-maven-plugin` plugin to use `hsqldb` db driver. 
   
      This driver is configured with `test` scope in the `pom.xml`. 
      You need to configure `spring-boot-maven-plugin` plugin with `useTestClasspath` property:
      ```xml
         <build>
          <plugins>
            <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
              <configuration>
                <useTestClasspath>true</useTestClasspath>
                ...
              </configuration>
            </plugin>
          </plugins>
         </build>
      ```
    - The application by default configures a connection string for db in memory. You don't need to specify db settings.
      But if you still want to set them explicitly in [the `application.yaml` from the test resources](src/test/resources/application.yaml)
      you could do that. But then you need to pass the file as:
      ```bash
      mvn spring-boot:run -Dspring-boot.run.arguments=--spring.config.import=file:./src/test/resources/application.yaml
      ```
      
3. Pass db connection via docker composition

   Set the db connections via the docker compose file:
   ```yaml
   services:
     db:
       image: mysql
       container_name: mysql-db
       environment:
         - MYSQL_ROOT_PASSWORD=root
         - MYSQL_DATABASE=my-test-db
       ports:
         - '3306:3306'
   
     app:
       
       environment:
         - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/my-test-db?useSSL=false&allowPublicKeyRetrieval=true
         - SPRING_DATASOURCE_USERNAME=root
         - SPRING_DATASOURCE_PASSWORD=root
   ```

### Pass db-connection data to the application. TODO list.

1. Add a db driver to the dependencies for prod:
```xml
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>${mysql-connector-java.version}</version>
    </dependency>
```
2. Add a db driver with a database in the memory to the dependencies for test purpose
```xml
    <dependency>
      <groupId>org.hsqldb</groupId>
      <artifactId>hsqldb</artifactId>
      <version>${hsqldb.version}</version>
      <scope>test</scope>
    </dependency>
```
3. Configure `spring-boot-maven-plugin` plugin with `useTestClasspath` property:
```xml
   <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <useTestClasspath>true</useTestClasspath>
          ...
        </configuration>
      </plugin>
    </plugins>
   </build>
```
4. Configure database servie and pass the related db settings to the application in docker compose:
```yaml
services:
  db:
    image: mysql
    container_name: mysql-db
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=my-test-db
    ports:
      - '3306:3306'

  app:
    
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/my-test-db?useSSL=false&allowPublicKeyRetrieval=true
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
```

### Running application:

We have different options:

#### via `mvn spring-boot:run` with `hsqldb` database in a memory

#### via docker composition and mysql docker container:

To build and run application (the 1st execution):

```bash
mvn clean install && docker compose up
```

To clear and restart the application, that has already been built and run:
```bash
./clearAndStart.sh
```

To drop containers together with mysql **volumes** and the app docker image:
```bash
docker rm $(docker ps -aq) && docker volume rm $(docker volume ls -q) && docker image rm jpa-liquibase:latest
```

To connect to the running mysql container (with `root` password):
```bash
$ mysql -h localhost -P 3306 --protocol=tcp -u root -p
```

### Test functionality on the running application:

Get all items:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/items/
```

Create an item:
```bash
curl -i -X POST -w "\n" -H 'Content-Type: application/json' -d '{"name":"A1","dateTime":"2025-10-10T20:45:00+05:00 "}' http://localhost:8080/rest/items
```

Update the item:
```bash
curl -i -X PUT -w "\n" -H 'Content-Type: application/json' -d '{"id": 1, "name":"A1 updated","dateTime":"2025-10-10T20:45:00+05:00 "}' http://localhost:8080/rest/items
```

Find the item by id:
```bash
curl -i -X GET -w "\n" http://localhost:8080/rest/items/1
```

### Steps to integrate Spring Boot with Spring Data Jpa and Liquibase

* [Initial project adoptions](#initial-project-adoptions)
* [Schema generation with Liquibase](#schema-generation-with-liquibase)
* [Jpa and Spring Data Jpa support](#jpa-and-spring-data-jpa-support)
* [Remove default integration test](#remove-default-integration-test)
* [Database Configuration](#database-configuration)
* [Docker composition with mysql and database connection](#docker-composition-with-mysql-and-database-connection)

### Initial project adoptions

1. Without using `spring-boot-starter-parent`:

```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.2</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>
```

We must define `spring-boot-dependencies` bom and configure main class in `spring-boot-maven-plugin`:
```xml
<project>
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>${spring.boot.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <mainClass>com.example.jpa.liquibase.Application</mainClass>
          <layout>JAR</layout>
        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>repackage</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```

Otherwise you'll get the error:
> no main manifest attribute, in application.jar

2. `ApplicationTests` package

When you rename/move packages, make sure that [`ApplicationTests`](src/test/java/com/example/jpa/liquibase/ApplicationTests.java) 
test that is triggered with `mvn clean package` and runs Spring Boot is located in the same package as 
[`Application`](src/main/java/com/example/jpa/liquibase/Application.java).

Otherwise you'll get the error:
> Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test

### Schema generation with Liquibase

1. Add `liquibase-core` in `pom.xml`:

```xml
    <dependency>
      <groupId>org.liquibase</groupId>
      <artifactId>liquibase-core</artifactId>
      <version>${liquibase.version}</version>
    </dependency>
```

2. Add [changelog master file](src/main/resources/db/changelog/db.changelog-master.yaml) with changelog files included.

By default, the master change log is read from `src/main/resources/db/changelog/db.changelog-master.yaml`, 
but you can change the location by setting `spring.liquibase.change-log`.

### Jpa and Spring Data Jpa support

1. Add `spring-boot-starter-data-jpa` in `pom.xml`:

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
```

This adds dependencies on:
* `org.hibernate.orm:hibernate.core` and
* `org.springframework.data:spring-data-jpa`

2. Add [Jpa entity](src/main/java/com/example/jpa/liquibase/repository/ItemEntity.java)

3. Add [Jpa repository interface](src/main/java/com/example/jpa/liquibase/repository/ItemRepository.java)

### Database Configuration

1. Add driver, used to connect to the database

```xml
  <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
  </dependency>
```

2. Configure database properties, that affect optimisations, but not connection itself

We can use:
* (preferably) either [Spring configuration file](src/main/resources/application.yaml)
* or (not recommended - additional complexity) [`persistence.xml`](src/main/resources/META-INF/persistence.xml)

[Use a Traditional persistence.xml File](https://docs.spring.io/spring-boot/how-to/data-access.html#howto.data-access.use-traditional-persistence-xml)

> Spring Boot will not search for or use a META-INF/persistence.xml by default. 
> If you prefer to use a traditional persistence.xml, 
> you need to define your own @Bean of type LocalEntityManagerFactoryBean (with an ID of ‘entityManagerFactory’) 
> and set the persistence unit name there.

So using `persistence.xml` makes the entire configuration more complicated.

**The same properties might have different names in those files**

**Note #1:**
We include into the project configuration only database properties, that affect optimisations

* change default transaction type (if needed)
* the mode of your 2nd level cache
* provider-specific configuration parameters (if needed)

**Note #2:**

The following properties should not be part of the project configuration
when using Spring Data Jpa together with Liquibase:

* the name of each persistence unit: `<persistence-unit name="my-persistence-unit">`
* which managed persistence classes are part of a persistence unit: 
  `<class>com.example.MyEntity</class>` or/and `<jar-file>my-entities.jar</jar-file>`
* the persistence provider that shall be used at runtime: `<provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>`
* dialect property: `<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>`
* the data source you want to use to connect to your database: `<jta-data-source>java:app/jdbc/MyDataSource</jta-data-source>`
* how to create and validate the database schema (we use `liquibase` instead): `jakarta.persistence.schema-generation.scripts`

**Note #3:**

The following properties must not be part of the built project. 
They belong to the infrastructure configuration and should be configured externally:
```
<property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver" />
<property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/jpaForBeginners" />
<property name="jakarta.persistence.jdbc.user" value="postgres" />
<property name="jakarta.persistence.jdbc.password" value="postgres" />
```

Exception: integration tests.

### Docker composition with mysql and database connection

We **do not configure database connection** in the Spring's main `application.yaml`.
Having such configuration in the file makes it built-time specific.
You must be able to change the configuration at run time, 
make them different for different environments without application rebuild.
Additionally such configuration is ITO, but not dev team responsibility.

We **do configure database connection** in the Spring's test `application.yaml`.
The purpose is to be able to run [`ApplicationTests`](src/test/java/com/example/jpa/liquibase/ApplicationTests.java) 
to check all injection/autowired points.

1. Add [`Dockerfile`](Dockerfile) file.
2. Add [Docker compose](docker-compose.yaml) file. Configure `db` and pass its params to the `app` containers.
3. Add in [Docker compose](docker-compose.yaml) support for debugging by passing: `- JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8787`
4. Add [`clearAndStart.sh` script](clearAndStart.sh) to
    * stop and drop the running `app` container
    * drop the app docker image
    * rebuild the project with maven and
    * start `db` and `app` containers via docker composition 

### Unit and integration tests

* We use unit tests with mocking services. Spring Boot is not loaded. Pure unit test and mockito library.
  See [`ItemServiceTest.java`](src/test/java/com/example/jpa/liquibase/service/ItemServiceTest.java)

  Note: 
  To support such fields, you must be able to inject mock services. 
  You need to have either setters, or make fields package private.
* We use integration tests that do load Spring Boot with mocking from Spring Boot.

  In the complex  project it still takes time to run such tests.
  A better approach is use pure unit tests with mockito. 

  See [`ItemServiceSbMocksIT.java`](src/test/java/com/example/jpa/liquibase/service/ItemServiceSbMocksIT.java) 
  or [`ItemServiceViaBeanConfigSbMocksIT.java`](src/test/java/com/example/jpa/liquibase/service/ItemServiceViaBeanConfigSbMocksIT.java)

* We use [`ItemRepositoryIT.java`](src/test/java/com/example/jpa/liquibase/repository/ItemRepositoryIT.java) 
  integration test with a real mysql database, run mysql in docker during integration test execution.
* Unit test to test only the queries of repositories, see [`ItemRepositoryTest.java`](src/test/java/com/example/jpa/liquibase/repository/ItemRepositoryTest.java)
  It still takes time to run such tests, better to mark them as integration tests, not unit tests.