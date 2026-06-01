#!/bin/sh

set -eu

. ../../scripts/aws-utils.sh

aws_login v2
load_env_vars

echo "Stopping existing DSA-services containers if present..."
docker compose down || true

echo "Starting DSA-services infrastructure..."
docker compose up -d
