#!/bin/sh

set -eu

SCRIPTS_DIR="$(pwd)"

export SCRIPTS_DIR

. "$SCRIPTS_DIR/env-vars/setup.sh"
. "$SCRIPTS_DIR/aws/setup.sh"
. "$SCRIPTS_DIR/images/setup.sh"
