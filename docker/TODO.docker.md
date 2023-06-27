
## Packaging SpringBoot applications with Docker

### Without Dockerfile
* [Using buildpacks](#using-buildpacks)
* 
### With Dockerfile

### Using buildpacks

Spring Boot provides support to directly build a Docker image from the source code.
Spring Boot uses [buildpacks](https://buildpacks.io/) to achieve this.

By default, Spring Boot uses the `artifactId:version` to build the image.
To customize the image name use:

```bash
-Dspring-boot.build-image.imageName=${customImageName}:${someVersion} 
```

Docker image from the source code

