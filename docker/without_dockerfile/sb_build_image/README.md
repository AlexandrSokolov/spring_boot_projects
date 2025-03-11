### Build using buildpacks

No `Dockerfile` is used. 

Spring Boot provides support to directly build a Docker image from the source code.
Spring Boot uses [buildpacks](https://buildpacks.io/) to achieve this.


**The rebasing support is the most notable.**

**Rebasing an image allows the layer or layers that contain the application code to be rebased on top
of a new operating system or JVM layer without having to rebuild the application.**

**_This allows OS and JVM security updates to be applied far more quickly_ than if every application**
**and its entire image had to be rebuilt in order to consume the update.**

#### Pros:
* The generated Docker image is multiple layers. So if we only change our application code, subsequent builds will be much faster:

#### Cons:
* You cannot control the parent image.
* You cannot reuse a Dockerfile. If there is a Dockerfile located within your source code repository, it will be ignored.


### Build and run the application

```bash
mvn clean package
mvn spring-boot:build-image
docker run -p 8080:9000 -e "JAVA_OPTS=-Xmx128m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8787" sb_demo/sb-build-image:1.0.0 --server.port=9000
```

### Docker Image Naming

By default, Spring Boot uses the `artifactId:version` to build the image.
To customize the image name use `spring-boot.build-image.imageName` variable:
```bash
mvn -Dspring-boot.build-image.imageName="sb_demo/sb-build-image:1.0.0" spring-boot:build-image
```

### Test the application with a curl request

```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest
```

### How code change affects the related images

```bash
# build `sb-build-image` docker image for the 1st time
$ mvn spring-boot:build-image
$ docker image ls
REPOSITORY                 TAG        IMAGE ID       CREATED        SIZE
paketobuildpacks/run       base-cnb   1c8659977d9c   3 weeks ago    87.1MB
paketobuildpacks/builder   base       8dcdb7d9ad4d   43 years ago   1.36GB
sb-build-image             1.0.0      01bdf024c6a4   43 years ago   275MB
# no changes in the code, build it again
$ mvn spring-boot:build-image
$ docker image ls
REPOSITORY                 TAG        IMAGE ID       CREATED        SIZE
paketobuildpacks/run       base-cnb   1c8659977d9c   3 weeks ago    87.1MB
paketobuildpacks/builder   base       8dcdb7d9ad4d   43 years ago   1.36GB
sb-build-image             1.0.0      01bdf024c6a4   43 years ago   275MB
# one symbol change in the code without version change:
$ mvn spring-boot:build-image
$ docker image ls
REPOSITORY                 TAG        IMAGE ID       CREATED        SIZE
paketobuildpacks/run       base-cnb   1c8659977d9c   3 weeks ago    87.1MB
paketobuildpacks/builder   base       8dcdb7d9ad4d   43 years ago   1.36GB
<none>                     <none>     01bdf024c6a4   43 years ago   275MB
sb-build-image             1.0.0      665e7320be19   43 years ago   275MB
```

The old `01bdf024c6a4` image still exists, but the new `665e7320be19` is defined as `sb-build-image:1.0.0`.  

