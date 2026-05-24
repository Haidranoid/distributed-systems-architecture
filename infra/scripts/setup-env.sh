#!/bin/sh

set -eu

. aws-utils.sh

if [ -n "${CI_PIPELINE_ID+x}" ]; then
  echo "GRADLE_USER_HOME:$GRADLE_USER_HOME"

  setup_aws_env v1
  show_aws_session

  export PROJECT_IMAGE_TAG="$AWS_ECR_DSA_REPOSITORY_NAME/$PROJECT_NAME"
  export PROJECT_IMAGE_ECR_TAG="$AWS_ECR_REGISTRY_URL/$PROJECT_IMAGE_TAG"

fi