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