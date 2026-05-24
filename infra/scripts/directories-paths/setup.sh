#!/bin/sh

set -eu

cd "$SCRIPTS_DIR/directories-paths"

. dsa-directories-paths.sh
. artifacts-directories-paths.sh
. infra-directories-paths.sh
. services-directories-paths.sh

cd "$SCRIPTS_DIR"