### Build the app itself with Maven on Docker and build the app image

Build the app:
```Dockerfile
FROM maven:3.9.2-eclipse-temurin-17 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package
```
Run the app:
```Dockerfile
# the second stage to run the application using open jdk 17 on alpine
FROM openjdk:17-alpine

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /target/sb-build-app-and-image-1.0.0.jar /sb-build-app-and-image.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/sb-build-app-and-image.jar"]
```

#### Pros:
* Does not require the build tool or JDK to be installed on the host machine beforehand (Docker controls the build process)

#### Cons:
* If the application layer is rebuilt, the mvn package command will force all Maven dependencies
  to be pulled from the remote repository all over again (you lose the local Maven cache)
* Not-layered app image

### Build image:

```bash
docker build -t sb_demo/sb_build_app_and_image:1.0.0 .
```

### Analyze the image:

```bash
$ docker run --rm -it  \
    -v /var/run/docker.sock:/var/run/docker.sock  wagoodman/dive:latest  \
    sb_demo/sb_build_app_and_image:1.0.0
```

### Run container:

```bash
$ docker run -d -p 8080:8080 sb_demo/sb_build_app_and_image:1.0.0
```

### Send request:

```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest
```