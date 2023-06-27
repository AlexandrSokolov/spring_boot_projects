### Docker 

1. Via [Spring Boot built-in containerization (Buildpacks)](https://paketo.io/) and `spring-boot:build-image`
Without adding a main class!!!
You cannot run application via java:
```
$ java -jar target/jakarta_resteasy-0.0.1-SNAPSHOT.jar
no main manifest attribute, in target/jakarta_resteasy-0.0.1-SNAPSHOT.jar
```

-Dspring-boot.build-image.imageName=myrepo/myimage

```bash
$ mvn spring-boot:build-image
$ docker image ls
REPOSITORY                                       TAG                       IMAGE ID       CREATED         SIZE
paketobuildpacks/run                             base-cnb                  1c8659977d9c   2 weeks ago     87.1MB
paketobuildpacks/builder                         base                      d37640437760   43 years ago    1.29GB
jakarta_resteasy                                 0.0.1-SNAPSHOT            bd499cb60597   43 years ago    282MB

$ docker run --name jakarta_resteasy -d -p 8080:8080 jakarta_resteasy:0.0.1-SNAPSHOT
$ docker container ls
CONTAINER ID   IMAGE                             COMMAND              CREATED         STATUS         PORTS                                       NAMES
c7a95606d545   jakarta_resteasy:0.0.1-SNAPSHOT   "/cnb/process/web"   3 minutes ago   Up 3 minutes   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   jakarta_resteas
```

mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild

2. Layered Jars


3. Make jar executable and build image from `Dockerimage`

### All get requests with curl command for check

### (Spring Boot and Rest Easy Spring Boot Starter issue)[https://github.com/resteasy/resteasy-spring-boot/issues/243]

### Simple check with idea rest clint or resteasy client

