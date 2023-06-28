### Overview of different approaches to package SpringBoot applications with Docker

<details>
<summary> With Dockerfile </summary>

#### Pros:
* You can set the parent image.
* You can reuse a Dockerfile and keep everything under your control.

#### Cons:

#### Options:

</details>


<details>
<summary> Without Dockerfile </summary>

#### Pros:
* The generated Docker image is multiple layers. So if we only change our application code, subsequent builds will be much faster:

#### Cons:
* A standard fat jar gets built.
* You cannot control the parent image.
* You cannot reuse a Dockerfile. If there is a Dockerfile located within your source code repository, it will be ignored.

#### Options:
* [Using buildpacks](#using-buildpacks)

</details>



### [Build using buildpacks](without_dockerfile/sb_build_image/README.md)

### [Build a layered jar](with_dockerfile/sb_build_app_and_image/README.md)

### [Build both SpringBoot application and Docker image via Docker](with_dockerfile/sb_build_app_and_image/README.md)

#### Pros:
* Does not require the build tool or JDK to be installed on the host machine beforehand (Docker controls the build process)

#### Cons:

* If the application layer is rebuilt, the mvn package command will force all Maven dependencies 
  to be pulled from the remote repository all over again (you lose the local Maven cache)




