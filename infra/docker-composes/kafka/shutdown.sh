#!/bin/sh

set -eu

echo "Stopping existing Kafka containers if present..."
docker compose down || true
