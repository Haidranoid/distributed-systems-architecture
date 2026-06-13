#!/bin/sh

set -eu

echo "Stopping existing Kafka containers if present..."
docker compose down || true

echo "Starting Kafka infrastructure..."
docker compose up -d
