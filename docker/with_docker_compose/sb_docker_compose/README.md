## Build the docker image with docker compose.

Docker composition allows you to:
1. configure different services (database, cron, logging)
2. simplify passing of the configuration parameters into a docker container (debug options, spring boot properties)
    Those parameters are stored in the yaml configuration file, not as part of the command that runs the container

- [Build, run and test the application via docker](#build-run-and-test-the-application-via-docker)
- [Docker compose file, explanation](#docker-compose-file)
- [Build the layered application, the details](#build-the-layered-application-the-details)
- [Pass command line arguments via docker compose](#pass-command-line-arguments-via-docker-compose)

### Build, run and test the application via docker

```bash
mvn clean package
docker compose up
curl -i -X GET -w "\n" http://localhost:8080/rest
```

You could also run docker container directly as:
```bash
docker run -p 8080:9000 -e "JAVA_OPTS=-Xmx128m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8787" sb_demo/sb-docker-compose:latest --server.port=9000
```

### [Docker compose file](docker-compose.yaml):
1. Defines the `app` service, builds the image using `Dockerfile` (see `context` attribute):
    ```yaml
      app:
        image: 'sb-docker-compose:latest'
        build:
          context: .
        container_name: app
    ```
2. Sets port mapping for the web server and for the debugging:
    ```yaml
        ports:
          - "8080:8080"
          - "8787:8787"
    ```
3. Starts docker container in the debugging mode via passing: `JAVA_OPTS`:
    ```yaml
        environment:
          - JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8787
    ```

### [Build the layered application, the details](../../with_dockerfile/sb_build_layered_jar/README.md)

### Pass command line arguments via docker compose

For certain command line arguments, there are equivalent system properties.
For instance the following commands are equivalent:
```bash
java -jar spring-5.jar --server.port=8083
java -jar -Dserver.port=8083 spring-5.jar
```

As a consequence, we can set `server.port` not as a command line argument, 
but as an environment variable in a compose file:

You configure both environment variables and command line arguments in the docker compose file as:
```yaml
    environment:
      - server.port=9090
      - JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8787
```
In the output you'll see:
```text
... o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 9090 (http)
```