#!/bin/sh

docker_login() {
  if ! require_ecr_env; then
    return 1
  fi

  echo "$AWS_ECR_AUTH_PASSWORD" \
    | docker login \
        --username AWS \
        --password-stdin \
        "$AWS_ECR_REGISTRY_URL"
}

require_ecr_env() {
  if [ -z "${AWS_ECR_AUTH_PASSWORD:-}" ]; then
    echo "ERROR: AWS_ECR_AUTH_PASSWORD is required" >&2
    return 1
  fi

  if [ -z "${AWS_ECR_REGISTRY_URL:-}" ]; then
    echo "ERROR: AWS_ECR_REGISTRY_URL is required" >&2
    return 1
  fi
}