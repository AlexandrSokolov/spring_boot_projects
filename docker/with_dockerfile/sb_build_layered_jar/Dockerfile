FROM bellsoft/liberica-openjdk-alpine:17 as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM bellsoft/liberica-openjdk-alpine:17
EXPOSE 8080

#Each COPY directive results in a new layer in the final Docker image.
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

# Now you can pass both environment variables and command line arguments as:
# docker run -p 8080:9000 -e "JAVA_OPTS=-Ddebug -Xmx128m" sb_demo/sb_build_layered_jar:1.0.0 --server.port=9000
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher ${0} ${@}"]
