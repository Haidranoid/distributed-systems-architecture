#!/usr/bin/env bash

set -euo pipefail

source ./../../env.sh

echo "Stopping existing TeamCity agents containers if present..."
docker compose -f "$TEAMCITY_COMPOSE_DIR/docker-compose-agents.yml" down || true

echo "Starting TeamCity agents..."
docker compose -f "$TEAMCITY_COMPOSE_DIR/docker-compose-agents.yml" up -d

echo ""
echo "TeamCity agents started successfully."
echo ""