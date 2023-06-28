FROM maven:3.9.2-eclipse-temurin-17 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package

# the second stage to run the application using open jdk 17 on alpine
FROM openjdk:17-alpine

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /target/sb-build-app-and-image-1.0.0.jar /sb-build-app-and-image.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/sb-build-app-and-image.jar"]
