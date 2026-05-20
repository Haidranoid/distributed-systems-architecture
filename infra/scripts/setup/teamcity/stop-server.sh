#!/usr/bin/env bash

set -euo pipefail

source ./../../env-vars/dsa-env-vars.sh

echo "Stopping existing TeamCity server containers if present..."
docker compose -f "$TEAMCITY_COMPOSE_DIR/docker-compose-server.yml" down || true
