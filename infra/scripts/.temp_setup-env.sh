#!/usr/bin/env bash

set -Eeuo pipefail

trap 'echo "[ERROR] line $LINENO: $BASH_COMMAND"' ERR


export SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

export SERVICES_COMPOSE_DIR="$SCRIPT_DIR/../docker-composes/services"
export KAFKA_COMPOSE_DIR="$SCRIPT_DIR/../docker-composes/kafka"

export SERVICES_COMPOSE_FILE="$SERVICES_COMPOSE_DIR/docker-compose.yml"
export KAFKA_COMPOSE_FILE="$KAFKA_COMPOSE_DIR/docker-compose.yml"


source aws-utils.sh
source docker-utils.sh
source composes-utils.sh