## Layered images created via `Dockerfile`

* Docker layered image is created with `Dockerfile`.
* You set the parent image.
* You can reuse a Dockerfile and keep everything under your control.

* [Layered jars, idea](#layered-jars-idea)
* [Creation of layered image](#creation-of-layered-image)
* [How a layered image gets created, in details](#how-a-layered-image-gets-created)
* [Docker specific commands to build and run](#docker-commands)
* [Test the application](#test-the-application)
* [Spring Boot Docker official guide](https://spring.io/guides/topicals/spring-boot-docker/)

### Layered jars, idea

An application code is likely what changes most frequently, so it gets its own layer. 
Further, each layer can evolve on its own, and only when a layer has changed will it be rebuilt for the Docker image.


A typical Spring Boot fat jar layout:

```text
org/
  springframework/
    boot/
  loader/
...
BOOT-INF/
  classes/
...
lib/
...
```

Main areas are:
* Bootstrap classes required to launch the Spring application
* 3rd party libraries
* Application code

Layered jars structure from `layers.idx`:

```text
- "dependencies":
  - "BOOT-INF/lib/"
- "spring-boot-loader":
  - "org/"
- "snapshot-dependencies":
- "application":
  - "BOOT-INF/classes/"
  - "BOOT-INF/classpath.idx"
  - "BOOT-INF/layers.idx"
  - "META-INF/"
```

### Creation of layered image

See [`Dockerfile`](Dockerfile)

1. Build the app code with `mvn clean package`
2. Extract the app layers into the folders on `builder` image:
    ```Dockerfile
    FROM bellsoft/liberica-openjdk-alpine:17 as builder
    ARG JAR_FILE=target/*.jar
    COPY ${JAR_FILE} application.jar
    RUN java -Djarmode=layertools -jar application.jar extract
    ```
3. Create a image layer per each extracted layer:
    ```Dockerfile
    #Each COPY directive results in a new layer in the final Docker image.
    COPY --from=builder dependencies/ ./
    COPY --from=builder snapshot-dependencies/ ./
    COPY --from=builder spring-boot-loader/ ./
    COPY --from=builder application/ ./
    ```

### How a layered image gets created

#### Build the application: `mvn clean package`

#### Examine the layers of the app jar manually:

```bash
$ java -Djarmode=layertools -jar target/sb-build-layered-jar-1.0.0.jar list
dependencies
spring-boot-loader
snapshot-dependencies
application
```
#### To see the content of these folders, extract layers (this command is used in [Dockerfile](Dockerfile)): 

`java -Djarmode=layertools -jar target/sb-build-layered-jar-1.0.0.jar extract`

### Docker commands

#### Build image:
 
```bash
docker build -t sb_demo/sb_build_layered_jar:1.0.0 .
```

#### To publish docker image:
* With docker: `$ docker push NAME[:TAG]`
* With maven `dockerfile-maven-plugin` from Spotify: `$ mvn dockerfile:push`

#### Analyze the image:

With `dive` running as a docker container:
```bash
$ docker run --rm -it  \
    -v /var/run/docker.sock:/var/run/docker.sock  wagoodman/dive:latest  \
    sb_demo/sb_build_layered_jar:1.0.0
```

With: `docker history sb_demo/sb_build_layered_jar:1.0.0`

#### Run container:

```bash
$ docker run -p 8080:8080 sb_demo/sb_build_layered_jar:1.0.0
```

Pass environment variables like `JAVA_OPTS` (for instance enabling debug mode for logging):
```bash
$ docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug -Xmx128m" sb_demo/sb_build_layered_jar:1.0.0 --server.port=9000
```

Pass command line arguments, like `--server.port`:
```bash
$ docker run -p 8080:9000 -e "JAVA_OPTS=-Xmx128m" sb_demo/sb_build_layered_jar:1.0.0 --server.port=9000
```

###  Test the application

The server is running on port 9090 (if run with `--server.port=9000`), 
but it is exposed locally to `8080` via `-p 8080:9000`:

Send a GET request:
```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest
````