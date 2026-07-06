#!/usr/bin/env bash

set -Eeuo pipefail

trap 'echo "[ERROR] line $LINENO: $BASH_COMMAND"' ERR

source aws-utils.sh
source docker-utils.sh
source composes-utils.sh