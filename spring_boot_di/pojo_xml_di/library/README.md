### Library with POJOs

The library defines api interfaces, the implementations and different services that inject dependencies with plain java.
No annotations, no spring-specific dependencies.

### Dependency to the library is defined in `pom.xml` as:

```xml
    <dependency>
      <groupId>com.example.lib.di.xml</groupId>
      <artifactId>xml-di-library</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
```

**The application/library client is responsible for creating XML configuration of the library classes, 
to make them injectable in the application.**

[`library-beans.xml`](src/main/resources/library-beans.xml) can be used as an example, but with certain changes, 
because the library creator cannot know in advance bean ids and names, used by the application/client.

[Configure IoC container of the application to use thislibrary](../application/README.md#configure-ioc-container-with-plain-java-library)