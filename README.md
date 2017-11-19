# Ohnana
[![Build Status](https://travis-ci.org/hahleung/ohnana-whats.svg?branch=master)](https://travis-ci.org/hahleung/ohnana-whats)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=ohnana:ohnana-whats)](https://sonarcloud.io/api/badges/gate?key=ohnana:ohnana-whats)

## Overview

The very begining of a personal project.

## Prerequisites

- JDK 1.8
- Docker (optional - only for local purposes)

## Project management

[See trello board](https://trello.com/b/J03PJN1p)

## Architecture overview

[See architecture overview](https://github.com/hahleung/ohnana-whats/wiki/Architecture)

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
```
localhost:8080/swagger-ui.html
```

## Deployment

A CI/CD strategy is in place.

`develop` is constantly built on changes, but not remotely deployed.

These branches are built and remotely deployed on changes:
- `master` built to the `staging` environment
- `prod` built to the `production` environment (WIP)

Statuses:
- `develop`: [![Build Status](https://travis-ci.org/hahleung/ohnana-whats.svg?branch=develop)](https://travis-ci.org/hahleung/ohnana-whats)
- `master`: [![Build Status](https://travis-ci.org/hahleung/ohnana-whats.svg?branch=master)](https://travis-ci.org/hahleung/ohnana-whats)
- `prod`: WIP

_DEPRECATED:_

Select `Dockerfile.api` (for `Docker-compose`) if asked.

```
./gradlew buildDocker
heroku container:push --recursive
```

## Misc sources

[See used documentation](https://github.com/hahleung/ohnana-whats/wiki/Documentation)

