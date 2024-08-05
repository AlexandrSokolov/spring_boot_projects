FROM bellsoft/liberica-openjdk-alpine:21

EXPOSE 8080

COPY target/jpa-liquibase-*.jar application.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar application.jar"]