#!/usr/bin/env bash

set -euo pipefail

if ! aws sts get-caller-identity > /dev/null 2>&1; then
  echo "AWS session expired or missing."
  aws login
fi