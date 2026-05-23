#!/bin/sh

set -eu

SCRIPTS_DIR="$(pwd)"

export SCRIPTS_DIR

. ./env-vars/setup.sh
. ./aws/setup.sh
. ./images/setup.sh
