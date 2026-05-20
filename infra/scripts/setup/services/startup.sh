#!/usr/bin/env bash

set -euo pipefail

source ./../../env.sh

echo "Stopping existing DSA-services containers if present..."
docker compose --project-directory "$SERVICES_COMPOSE_DIR" down || true

echo "Starting DSA-services infrastructure..."
docker compose --project-directory "$SERVICES_COMPOSE_DIR" up -d
