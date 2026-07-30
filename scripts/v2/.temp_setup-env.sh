#!/usr/bin/env bash

set -Eeuo pipefail

trap 'echo "[ERROR] line $LINENO: $BASH_COMMAND"' ERR


export SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

export COMPOSE_SERVICES_DIR="$SCRIPT_DIR/../composes/services"
export COMPOSE_KAFKA_DIR="$SCRIPT_DIR/../composes/kafka"

export COMPOSE_SERVICES_FILE="$COMPOSE_SERVICES_DIR/docker-compose.yml"
export COMPOSE_KAFKA_FILE="$COMPOSE_KAFKA_DIR/docker-compose.yml"


source aws-utils.sh
source docker-utils.sh
source composes-utils.sh


# =============================================

#!/bin/sh

set -eu

PROJECT_ROOT="$(pwd)"

export PROJECT_ROOT

export COMPOSE_SERVICES_DIR="$PROJECT_ROOT/infra/composes/services"
export COMPOSE_KAFKA_DIR="$PROJECT_ROOT/infra/composes/kafka"

export COMPOSE_SERVICES_FILE="$COMPOSE_SERVICES_DIR/docker-compose.yml"
export COMPOSE_KAFKA_FILE="$COMPOSE_KAFKA_DIR/docker-compose.yml"

. "$PROJECT_ROOT/infra/scripts/aws-utils.sh"
. "$PROJECT_ROOT/infra/scripts/docker-utils.sh"
. "$PROJECT_ROOT/infra/scripts/composes-utils.sh"