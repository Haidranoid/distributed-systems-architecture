#!/bin/sh

run_compose() {
  compose_name="${1:-}"

  if [ -z "$compose_name" ]; then
    echo "ERROR: compose_name argument is required" >&2
    return 1
  fi

  if ! validate_compose_name "$compose_name"; then
    echo "ERROR: unknown compose_name: $compose_name" >&2
    return 1
  fi

  create_network

  if [ "$compose_name" = "services" ]; then
    startup_services_compose
  elif [ "$compose_name" = "kafka" ]; then
    startup_kafka_compose
  fi
}

stop_compose() {
  compose_name="${1:-}"

  if [ -z "$compose_name" ]; then
    echo "ERROR: compose_name argument is required" >&2
    return 1
  fi

  if ! validate_compose_name "$compose_name"; then
    echo "ERROR: unknown compose_name: $compose_name" >&2
    return 1
  fi

  if [ "$compose_name" = "services" ]; then
    shutdown_services_compose
  elif [ "$compose_name" = "kafka" ]; then
    shutdown_kafka_compose
  fi

  remove_network_if_unused
}

validate_compose_name() {
  compose_name="${1:-}"

  [ "$compose_name" = "services" ] || [ "$compose_name" = "kafka" ]
}

create_network() {
  docker network inspect distributed-systems >/dev/null 2>&1 \
    || docker network create distributed-systems
}

remove_network_if_unused() {

  container_count="$(
    docker network inspect distributed-systems \
      --format '{{len .Containers}}' \
      2>/dev/null || echo 0
  )"

  if [ "$container_count" -eq 0 ]; then
    docker network rm distributed-systems >/dev/null 2>&1 || true
  fi
}

startup_services_compose() {

  echo "Stopping existing DSA-services containers if present..."
  docker compose \
    -f ../docker-composes/services/docker-compose.yml \
    down || true

  echo "Building DSA-services images..."
  docker compose \
    -f ../docker-composes/services/docker-compose.yml \
    build

  echo "Starting DSA-services infrastructure..."
  docker compose \
    -f ../docker-composes/services/docker-compose.yml \
    up -d
}

shutdown_services_compose() {

  echo "Stopping DSA-services containers..."
  docker compose \
    -f ../docker-composes/services/docker-compose.yml \
    down || true
}

startup_kafka_compose() {

  echo "Stopping existing Kafka containers if present..."
  docker compose \
    -f ../docker-composes/kafka/docker-compose.yml \
    down || true

  echo "Starting Kafka infrastructure..."
  docker compose \
    -f ../docker-composes/kafka/docker-compose.yml \
    up -d
}

shutdown_kafka_compose() {

  echo "Stopping Kafka containers..."
  docker compose \
    -f ../docker-composes/kafka/docker-compose.yml \
    down || true
}