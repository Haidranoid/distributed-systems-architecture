#!/bin/sh

set -eu

echo "Stopping existing DSA-services containers if present..."
docker compose down || true
