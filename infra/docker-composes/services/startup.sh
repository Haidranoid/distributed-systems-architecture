#!/usr/bin/env bash

set -euo pipefail

source ../../env.sh
source "$AWS_SCRIPTS_DIR/utils.sh"

setup_aws_env v2

echo "Stopping existing DSA-services containers if present..."
docker compose down || true

echo "Starting DSA-services infrastructure..."
docker compose up -d
