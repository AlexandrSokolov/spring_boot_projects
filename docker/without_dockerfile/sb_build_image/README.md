Note: if there is a Dockerfile located within your source code repository, it will be ignored.

Spring Boot provides support to directly build a Docker image from the source code.
Spring Boot uses [buildpacks](https://buildpacks.io/) to achieve this.

By default, Spring Boot uses the `artifactId:version` to build the image.
To customize the image name use:

```bash
-Dspring-boot.build-image.imageName=${customImageName}:${someVersion} 
```


```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest
```

```bash
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
# one symbole change in the code without version change:
$ mvn spring-boot:build-image
$ docker image ls
REPOSITORY                 TAG        IMAGE ID       CREATED        SIZE
paketobuildpacks/run       base-cnb   1c8659977d9c   3 weeks ago    87.1MB
paketobuildpacks/builder   base       8dcdb7d9ad4d   43 years ago   1.36GB
<none>                     <none>     01bdf024c6a4   43 years ago   275MB
sb-build-image             1.0.0      665e7320be19   43 years ago   275MB
```