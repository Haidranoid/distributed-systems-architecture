#!/bin/sh

set -eu

export AWS_ECR_AUTH_PASSWORD="$(
  aws ecr get-login-password --output text
)"
export AWS_CODEARTIFACT_AUTH_TOKEN="$(
  aws codeartifact get-authorization-token \
    --domain artifacts \
    --query authorizationToken \
    --output text
)"