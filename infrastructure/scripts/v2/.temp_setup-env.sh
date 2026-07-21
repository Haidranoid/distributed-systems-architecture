#!/usr/bin/env bash

set -Eeuo pipefail

trap 'echo "[ERROR] line $LINENO: $BASH_COMMAND"' ERR


export SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

export SERVICES_COMPOSE_DIR="$SCRIPT_DIR/../composes/services"
export KAFKA_COMPOSE_DIR="$SCRIPT_DIR/../composes/kafka"

export SERVICES_COMPOSE_FILE="$SERVICES_COMPOSE_DIR/docker-compose.yml"
export KAFKA_COMPOSE_FILE="$KAFKA_COMPOSE_DIR/docker-compose.yml"


source aws-utils.sh
source docker-utils.sh
source composes-utils.sh


# =============================================

#!/bin/sh

set -eu

PROJECT_ROOT="$(pwd)"

export PROJECT_ROOT

export SERVICES_COMPOSE_DIR="$PROJECT_ROOT/infra/composes/services"
export KAFKA_COMPOSE_DIR="$PROJECT_ROOT/infra/composes/kafka"

export SERVICES_COMPOSE_FILE="$SERVICES_COMPOSE_DIR/docker-compose.yml"
export KAFKA_COMPOSE_FILE="$KAFKA_COMPOSE_DIR/docker-compose.yml"

. "$PROJECT_ROOT/infra/scripts/aws-utils.sh"
. "$PROJECT_ROOT/infra/scripts/docker-utils.sh"
. "$PROJECT_ROOT/infra/scripts/composes-utils.sh"