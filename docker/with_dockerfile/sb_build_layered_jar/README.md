## Layered jars.

* [Motivation](#motivation-to-have-layered-jar)
* [Creation of layered jar](#creation-of-layered-jars)
* [Docker specific commands](#docker-specific-commands)
* [Spring Boot Docker official guide](https://spring.io/guides/topicals/spring-boot-docker/)

### Motivation to have layered jar

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

### Creation of layered jars

Configure `spring-boot-maven-plugin`:
```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <layers>
            <enabled>true</enabled>
          </layers>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

#### To examine the layers of any layered jar manually:

```bash
$ java -Djarmode=layertools -jar target/sb-build-layered-jar-1.0.0.jar list
dependencies
spring-boot-loader
snapshot-dependencies
application
```

#### To extract layers (this command is used in [Dockerfile](Dockerfile)): 

`java -Djarmode=layertools -jar target/sb-build-layered-jar-1.0.0.jar extract`

#### [Creating the Docker Image](Dockerfile)

### Docker specific commands

#### Build image:
* With docker cmd: `$ docker build -t sb_demo/sb_build_layered_jar:1.0.0 .

    Notes: 
    * you are responsible for setting docker repository and tag
    * only your image is generated, the base image is not downloaded:
      ```bash
      $ docker image ls
      REPOSITORY                     TAG       IMAGE ID       CREATED         SIZE
      sb_demo/sb_build_layered_jar   1.0.0     72840633e994   6 seconds ago   145MB
      ```

* With maven [`dockerfile-maven-plugin`](pom.xml) from Spotify: `$ mvn dockerfile:build`, 

  Notes: 
    * docker repository and the tag (package version) are taken from the maven configuration
    * the base image (`bellsoft/liberica-openjdk-alpine` in our case) is downloaded and is stored locally:
      ```bash
      $ docker image ls
      REPOSITORY                         TAG       IMAGE ID       CREATED          SIZE
      sb_demo/sb-build-layered-jar       1.0.0     414728b3ef36   19 seconds ago   145MB
      bellsoft/liberica-openjdk-alpine   17        60522fc45417   5 weeks ago      126MB
      ```
    * there is some bug with the maven plugin, the 3rd image is produced with no tag.

#### To publish docker image:
* With docker: `$ docker push NAME[:TAG]`
* With maven `dockerfile-maven-plugin` from Spotify: `$ mvn dockerfile:push`

#### Analyze the image:

```bash
$ docker run --rm -it  \
    -v /var/run/docker.sock:/var/run/docker.sock  wagoodman/dive:latest  \
    sb_demo/sb_build_layered_jar:1.0.0
```

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

#### Send request (server is on port 9090, locally it is exposed to 8080):

```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest
````