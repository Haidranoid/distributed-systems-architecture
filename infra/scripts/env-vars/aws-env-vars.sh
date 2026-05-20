#!/usr/bin/env bash

set -euo pipefail

export ECR_REPOSITORY_URL="314812911342.dkr.ecr.us-east-1.amazonaws.com"

export CODEARTIFACT_AUTH_TOKEN=$(
  aws codeartifact get-authorization-token \
    --domain artifacts \
    --query authorizationToken \
    --output text
)

export ECR_AUTH_PASSWORD=$(
  aws ecr get-login-password \
  --output text
)