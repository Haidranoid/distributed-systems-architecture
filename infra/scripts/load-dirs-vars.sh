#!/bin/sh

export SERVICES_COMPOSE_DIR="$SCRIPTS_DIR/../composes/services"
export KAFKA_COMPOSE_DIR="$SCRIPTS_DIR/../composes/kafka"

export SERVICES_COMPOSE_FILE="$SERVICES_COMPOSE_DIR/docker-compose.yml"
export KAFKA_COMPOSE_FILE="$KAFKA_COMPOSE_DIR/docker-compose.yml"
