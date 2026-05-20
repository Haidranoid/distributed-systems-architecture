#!/usr/bin/env bash

set -euo pipefail

source ./../../env.sh

echo "Stopping existing TeamCity server container if present..."
docker compose -f "$TEAMCITY_COMPOSE_DIR/docker-compose-server.yml" down || true

echo "Starting TeamCity server..."
docker compose -f "$TEAMCITY_COMPOSE_DIR/docker-compose-server.yml" up -d

echo ""
echo "TeamCity server started successfully."
echo "Open: $TEAMCITY_SERVER_URL"
echo ""

sleep 5

# Open browser automatically if available
if command -v xdg-open > /dev/null 2>&1; then
  xdg-open "$TEAMCITY_SERVER_URL" > /dev/null 2>&1 &
fi
