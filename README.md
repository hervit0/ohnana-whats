# Ohnana
[![Build Status](https://travis-ci.org/hahleung/ohnana-whats.svg?branch=master)](https://travis-ci.org/hahleung/ohnana-whats)
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

```
curl -X POST \
  http://localhost:8080/api/v1/session \
  -H 'content-type: application/json' \
  -d '{
	"players": [
		{ "name": "hervito", "id": 1, "order": 1, "team": 1 }
	]
}'
```

## Deployment

Statuses:
- `develop`: [![Build Status](https://travis-ci.org/hahleung/ohnana-whats.svg?branch=develop)](https://travis-ci.org/hahleung/ohnana-whats)
- `master`: [![Build Status](https://travis-ci.org/hahleung/ohnana-whats.svg?branch=master)](https://travis-ci.org/hahleung/ohnana-whats)
- `prod`: WIP

[See more about the CI/CD strategy in place](https://github.com/hervit0/ohnana-whats/wiki/Continuous-Integration)

## Misc sources

[See used documentation](https://github.com/hervit0/ohnana-whats/wiki/Documentation)

[Troubleshooting!](https://github.com/hervit0/ohnana-whats/wiki/Troubleshooting)

