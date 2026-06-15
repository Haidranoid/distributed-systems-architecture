#!/bin/sh

docker_login() {
  if verify_aws_session && [ -n "${AWS_ACCOUNT_ID+x}" ]; then
    echo "$AWS_ECR_AUTH_PASSWORD" | docker login --username AWS --password-stdin "$AWS_ECR_REGISTRY_URL"

    echo "SUCCESS: already logged to docker registry: $AWS_ECR_REGISTRY_URL" >&2
    return 0
  else
    echo "ERROR: aws session is required" >&2
    return 1
  fi
}
