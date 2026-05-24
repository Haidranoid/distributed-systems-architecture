#!/bin/sh

set -eu

AWS_ECR_AUTH_PASSWORD="$(
  aws ecr get-login-password --output text
)"
AWS_CODEARTIFACT_AUTH_TOKEN="$(
  aws codeartifact get-authorization-token \
    --domain artifacts \
    --query authorizationToken \
    --output text
)"

export AWS_ECR_AUTH_PASSWORD
export AWS_CODEARTIFACT_AUTH_TOKEN