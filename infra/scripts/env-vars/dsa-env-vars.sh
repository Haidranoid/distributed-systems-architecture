#!/usr/bin/env bash

set -euo pipefail

export DSA_ROOT_DIR="$HOME/IdeaProjects/distributed-systems-architecture"
export INFRA_DIR="$DSA_ROOT_DIR/infra"
export COMPOSE_DIR="$INFRA_DIR/compose"

export SERVICES_COMPOSE_DIR="$COMPOSE_DIR/services"
export TEAMCITY_COMPOSE_DIR="$COMPOSE_DIR/teamcity"

export TEAMCITY_SERVER_URL="http://localhost:8111"
