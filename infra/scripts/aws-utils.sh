#!/bin/sh

aws_login() {
  aws_version="${1:-}"

  if [ -z "$aws_version" ]; then
    echo "ERROR: aws version argument is required (v1|v2)" >&2
    return 1
  fi

  if is_logged; then
    echo "SUCCESS: already logged with aws version: $aws_version"
    return 0
  fi

  if ! validate_aws_version "$aws_version"; then
    echo "ERROR: unsupported aws version: $aws_version" >&2
    return 1
  fi

  if [ "$aws_version" = "v2" ]; then
    aws login
  elif [ "$aws_version" = "v1" ]; then
    aws configure set region "$CI_AWS_DEFAULT_REGION"
    aws configure set aws_access_key_id "$CI_AWS_ACCESS_KEY_ID"
    aws configure set aws_secret_access_key "$CI_AWS_SECRET_ACCESS_KEY"
  fi
}

load_env_vars() {
  load_account_vars
  load_ecr_vars
  load_codeartifact_vars
}

load_account_vars() {
  AWS_ACCOUNT_ID="$(aws sts get-caller-identity --query Account --output text)"
  AWS_REGION="$(aws configure get region)"

  export AWS_ACCOUNT_ID
  export AWS_REGION
}

load_ecr_vars() {
  AWS_ECR_REGISTRY_URL="$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com"
  AWS_ECR_DSA_REPOSITORY_URL="$AWS_ECR_REGISTRY_URL/dsa"
  AWS_ECR_AUTH_PASSWORD="$(aws ecr get-login-password --output text)"

  if [ -n "${PROJECT_DIR+x}" ]; then
    PROJECT_IMAGE_TAG="$PROJECT_DIR"
    PROJECT_IMAGE_ECR_TAG="$AWS_ECR_DSA_REPOSITORY_URL/$PROJECT_IMAGE_TAG"
  else
    PROJECT_IMAGE_TAG="undefined_project_image_tag"
    PROJECT_IMAGE_ECR_TAG="undefined_project_image_ecr_tag"
  fi

  export AWS_ECR_REGISTRY_URL
  export AWS_ECR_DSA_REPOSITORY_URL
  export AWS_ECR_AUTH_PASSWORD
  export PROJECT_IMAGE_TAG
  export PROJECT_IMAGE_ECR_TAG
}

load_codeartifact_vars() {
  AWS_CODEARTIFACT_DOMAIN_NAME="artifacts"
  AWS_CODEARTIFACT_SHARED_STARTER_REPOSITORY_NAME="shared-starter"

  AWS_CODEARTIFACT_URL="https://$AWS_CODEARTIFACT_DOMAIN_NAME-$AWS_ACCOUNT_ID.d.codeartifact.$AWS_REGION.amazonaws.com/maven"
  AWS_CODEARTIFACT_SHARED_STARTER_URL="$AWS_CODEARTIFACT_URL/$AWS_CODEARTIFACT_SHARED_STARTER_REPOSITORY_NAME"

  AWS_CODEARTIFACT_AUTH_TOKEN="$(
    aws codeartifact get-authorization-token \
      --domain "$AWS_CODEARTIFACT_DOMAIN_NAME" \
      --query authorizationToken \
      --output text
  )"

  export AWS_CODEARTIFACT_DOMAIN_NAME
  export AWS_CODEARTIFACT_SHARED_STARTER_REPOSITORY_NAME
  export AWS_CODEARTIFACT_URL
  export AWS_CODEARTIFACT_SHARED_STARTER_URL
  export AWS_CODEARTIFACT_AUTH_TOKEN
}

is_logged() {
  aws sts get-caller-identity >/dev/null 2>&1
}

show_aws_session() {
  aws configure list
}

validate_aws_version() {
  aws_version="${1:-}"

  [ "$aws_version" = "v1" ] || [ "$aws_version" = "v2" ]
}
