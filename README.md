# Ohnana

## Overview

The very begining of a personal project.

## Prerequisites

WIP - TO BE REFINED
- JDK 1.8
- Docker (optional)

## Project management

[Trello board](https://trello.com/b/J03PJN1p)

## Run locally

**With Gradlew**:
```
./gradlew build run
```

**With Docker**:

Build: `./gradlew clean build buildDocker`

Run: `docker run -p 8080:8080 -t ohnana/ohnana`

Stop the container:
```
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                    NAMES
6bb016d96db6        ohnana/ohnana     "/bin/sh -c 'exec ..."   About a minute ago   Up About a minute   0.0.0.0:8080->8080/tcp   jolly_bardeen
$ docker stop 6bb016d96db6
```

**With Docker-compose**:

Highly recommended, it runs all the attached microservices of this project.

Run: `docker-compose up`

## Run unit test suite

WIP

## Run BDD test suite

WIP

## Endpoints

TO BE TIDY AND EXTRACT SOMEWHERE ELSE

```
curl localhost:8080
```

## Misc sources
- https://spring.io/guides/gs/spring-boot/#scratch
- https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:wrapper_generation
- https://spring.io/guides/gs/spring-boot-docker/
- http://www.baeldung.com/dockerizing-spring-boot-application
- https://docs.docker.com/compose/compose-file/