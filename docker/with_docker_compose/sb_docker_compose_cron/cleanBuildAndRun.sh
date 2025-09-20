#!/usr/bin/env bash

docker compose down && \
  docker image rm sb_demo/sb-docker-cron:latest && \
  mvn clean package && \
  docker compose up