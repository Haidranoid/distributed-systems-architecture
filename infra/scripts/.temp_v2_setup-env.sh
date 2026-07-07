#!/bin/sh

#set -eu

PROJECT_ROOT="$(pwd)"

export PROJECT_ROOT

export SERVICES_COMPOSE_DIR="$PROJECT_ROOT/infra/docker-composes/services"
export KAFKA_COMPOSE_DIR="$PROJECT_ROOT/infra/docker-composes/kafka"

export SERVICES_COMPOSE_FILE="$SERVICES_COMPOSE_DIR/docker-compose.yml"
export KAFKA_COMPOSE_FILE="$KAFKA_COMPOSE_DIR/docker-compose.yml"

. aws-utils.sh
. docker-utils.sh
. composes-utils.sh