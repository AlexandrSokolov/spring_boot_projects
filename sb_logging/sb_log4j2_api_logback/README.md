
`spring-boot-starter-logging` shipped with `log4j-to-slf4j` to translate log4j calls to SLF4J API (Log4j-to-SLF4J bridge):

```text
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:3.5.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.5.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.5.0:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
```

As a result with default Spring Boot dependencies you can use `log4j` api in your project 
(and `logback` - as logging implementation)

See [`sb_slf4j_logback`](../sb_slf4j_logback/README.md) project