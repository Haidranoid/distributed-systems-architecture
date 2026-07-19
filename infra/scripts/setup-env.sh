#!/bin/sh

#set -eu

# This script is intended to be sourced from the infra/scripts directory.
# Example:
#   cd infra/scripts
#   . setup-env.sh

SCRIPTS_DIR="$(pwd)"
export SCRIPTS_DIR

. load-dirs-vars.sh
. load-scripts-utils.sh