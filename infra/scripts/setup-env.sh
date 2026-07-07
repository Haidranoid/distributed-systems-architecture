#!/bin/sh

#set -eu

# This script is intended to be sourced from the infra/scripts directory.
# Example:
#   cd infra/scripts
#   . setup-env.sh

SCRIPTS_DIR="$(pwd)"
export SCRIPTS_DIR

export SERVICES_COMPOSE_DIR="$SCRIPTS_DIR/../docker-composes/services"
export KAFKA_COMPOSE_DIR="$SCRIPTS_DIR/../docker-composes/kafka"

export SERVICES_COMPOSE_FILE="$SERVICES_COMPOSE_DIR/docker-compose.yml"
export KAFKA_COMPOSE_FILE="$KAFKA_COMPOSE_DIR/docker-compose.yml"

. aws-utils.sh
. docker-utils.sh
. composes-utils.sh