#!/usr/bin/env bash

# to delete all volumes:
# docker rm $(docker ps -aq) && docker volume rm $(docker volume ls -q) && docker image rm sb-external-config:latest

docker container rm sb-external-config-app && \
  docker image rm sb-external-config && \
  mvn clean install && \
  docker compose up
