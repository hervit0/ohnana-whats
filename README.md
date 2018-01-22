# Ohnana
[![Build Status](https://travis-ci.org/hervit0/ohnana-whats.svg?branch=master)](https://travis-ci.org/hervit0/ohnana-whats)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=ohnana:ohnana-whats)](https://sonarcloud.io/dashboard?id=ohnana%3Aohnana-whats)

## Overview

Ohnana, what's... ?

## Prerequisites

- JDK 1.8
- Docker (optional - only for local purposes)

## Project management

[See trello board](https://trello.com/b/J03PJN1p)

## Architecture/Technologies overview

[See architecture overview](https://github.com/hervit0/ohnana-whats/wiki/Architecture)

[See technologies overview](https://github.com/hervit0/ohnana-whats/wiki/Technologies)

## Run locally

**With Gradlew**:

Run: `./gradlew clean build run`

Run without test: `./gradlew clean build run -x test`

**With Docker-compose**:

Run: `./gradlew runComposite`

## Run unit test suite

Run: `./gradlew test`

## Run BDD test suite

[WIP]

## Endpoints

Self-generated documentation, spin locally (or visit remotely):
```
localhost:8080/swagger-ui.html
```

## Deployment

Statuses:
- `develop`: [![Build Status](https://travis-ci.org/hervit0/ohnana-whats.svg?branch=develop)](https://travis-ci.org/hervit0/ohnana-whats)
- `master`: [![Build Status](https://travis-ci.org/hervit0/ohnana-whats.svg?branch=master)](https://travis-ci.org/hervit0/ohnana-whats)
- `prod`: WIP

[See more about the CI/CD strategy in place](https://github.com/hervit0/ohnana-whats/wiki/Continuous-Integration)

## Misc sources

[See used documentation](https://github.com/hervit0/ohnana-whats/wiki/Documentation)

[Troubleshooting!](https://github.com/hervit0/ohnana-whats/wiki/Troubleshooting)

