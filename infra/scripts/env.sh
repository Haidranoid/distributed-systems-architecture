#!/bin/sh

set -eu

SCRIPTS_DIR="$(pwd)"

export SCRIPTS_DIR

. directories-paths/setup.sh
. aws/setup.sh
. images/setup.sh
