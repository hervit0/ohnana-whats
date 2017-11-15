# Ohnana

## Overview

The very begining of a personal project.

## Prerequisites

WIP - TO BE REFINED
- JDK 1.8
- Docker (optional)
- Gradlew wrapper: `gradle wrapper --gradle-version 2.0`

## Project management

[Trello board](https://trello.com/b/J03PJN1p)

## Run locally

**With Gradlew**:

Run: `./gradlew clean build run`

**With Docker**:

Run: `./gradlew runDocker`

**With Docker-compose**:

Highly recommended, it runs all the attached microservices of this project.

Run: `./gradlew runDockerCompose`

## Run unit test suite

WIP

## Run BDD test suite

WIP

## Endpoints

Self-generated documentation, spin locally (or visit remotely):
`localhost:8080/swagger-ui.html`

TO BE TIDY AND EXTRACT SOMEWHERE ELSE
```
curl localhost:8080/ohnana
```

## Misc sources
- https://spring.io/guides/gs/spring-boot/#scratch
- https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:wrapper_generation
- https://spring.io/guides/gs/spring-boot-docker/
- http://www.baeldung.com/dockerizing-spring-boot-application
- https://docs.docker.com/compose/compose-file/