#!/usr/bin/env bash

set -euo pipefail

echo "Stopping existing DSA-services containers if present..."
docker compose down || true
