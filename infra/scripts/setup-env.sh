#!/bin/sh

set -eu

. aws-utils.sh

if [ -n "${CI_PIPELINE_ID+x}" ]; then
  aws_login v1
  load_env_vars "$PROJECT_DIR"
else
  aws_login v2
fi

show_aws_session