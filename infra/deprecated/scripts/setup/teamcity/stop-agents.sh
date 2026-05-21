#!/usr/bin/env bash

set -euo pipefail

source ./../../env-vars/dsa-env-vars.sh

echo "Stopping existing TeamCity agents containers if present..."
docker compose -f "$TEAMCITY_COMPOSE_DIR/docker-compose-agents.yml" down || true
