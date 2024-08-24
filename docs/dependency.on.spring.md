- [Adding `spring-boot` dependencies via `spring-boot-starter-parent`](#adding-spring-boot-dependencies-via-spring-boot-starter-parent)
- [Adding `spring-boot` dependencies with no `spring-boot-starter-parent`](#adding-spring-boot-dependencies-with-no-spring-boot-starter-parent)

### Adding `spring-boot` dependencies via `spring-boot-starter-parent`

```xml
<project>
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent -->
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.3</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <groupId>com.my.group</groupId>
  <artifactId>my-project</artifactId>
  <version>1.0.0</version>

  <name>My Project</name>

  <properties>
    <java.version>21</java.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>
    
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
  
</project>
```

### Adding `spring-boot` dependencies with no `spring-boot-starter-parent`

```xml
<project>
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.my.group</groupId>
  <artifactId>my-project</artifactId>
  <version>1.0.0</version>

  <name>My Project</name>
  
  <properties>
    <spring.boot.version>3.1.0</spring.boot.version>
    <java.version>21</java.version>    
    <maven.compiler.source>${java.version}</maven.compiler.source>
    <maven.compiler.target>${java.version}</maven.compiler.target>
    <resource.delimiter>@</resource.delimiter>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>  
  </properties>

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

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>
    
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
  
</project>
```
