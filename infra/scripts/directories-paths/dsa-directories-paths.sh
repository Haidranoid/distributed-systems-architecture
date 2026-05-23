#!/bin/sh

set -eu

DSA_DIR="$(cd "$SCRIPTS_DIR/../.." && pwd)"

export DSA_DIR
export ARTIFACTS_DIR="$DSA_DIR/artifacts"
export INFRA_DIR="$DSA_DIR/infra"
export SERVICES_DIR="$DSA_DIR/services"