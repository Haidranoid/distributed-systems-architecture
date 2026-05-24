#!/bin/sh

set -eu

setup_aws_env() {
  aws_version="${1:-}"

  if ! validate_aws_version "$aws_version"; then
    echo "ERROR: unsupported aws version: $aws_version" >&2
    return 1
  fi

  if ! is_logged; then
    aws_login "$aws_version" || return 1
  fi

  load_env_vars
}

validate_aws_version() {
  aws_version="${1:-}"

  [ "$aws_version" = "v1" ] || [ "$aws_version" = "v2" ]
}

is_logged() {
  aws sts get-caller-identity > /dev/null 2>&1
}

aws_login() {
  aws_version="${1:-}"

  if [ "$aws_version" = "v2" ]; then
    aws login
  elif [ "$aws_version" = "v1" ]; then
    aws configure set region "$AWS_DEFAULT_REGION"
    aws configure set aws_access_key_id "$AWS_ACCESS_KEY_ID"
    aws configure set aws_secret_access_key "$AWS_SECRET_ACCESS_KEY"
  else
    echo "ERROR: invalid aws version" >&2
    return 1
  fi
}

load_env_vars() {
  . "$AWS_SCRIPTS_DIR/aws-env-vars.sh"
}