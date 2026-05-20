#!/usr/bin/env bash

set -euo pipefail

export SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

source "$SCRIPT_DIR/env-vars/dsa-env-vars.sh"
source "$SCRIPT_DIR/credentials/aws-login.sh"
source "$SCRIPT_DIR/env-vars/aws-env-vars.sh"