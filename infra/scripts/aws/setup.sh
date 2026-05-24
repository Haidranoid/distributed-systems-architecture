#!/bin/sh

set -eu

cd "$SCRIPTS_DIR/aws"

. aws-env-vars.sh
. aws-utils.sh

cd "$SCRIPTS_DIR"
