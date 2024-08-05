#!/usr/bin/env bash

# to delete all volumes:
# docker rm $(docker ps -aq) && docker volume rm $(docker volume ls -q) && docker image rm jpa-liquibase:latest

docker container rm app && \
  docker image rm jpa-liquibase:latest && \
  mvn clean install && \
  docker compose up
