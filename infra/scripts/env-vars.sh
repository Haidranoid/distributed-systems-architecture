#!/bin/sh

set -eu


#export DOCKER_COMPOSES_DIR="$INFRA_DIR/docker-composes"
#export K8S_DIR="$INFRA_DIR/k8s"
#export SCRIPTS_DIR="$INFRA_DIR/scripts"

if [ -n "${CI_PIPELINE_ID+x}" ]; then
  export GRADLE_USER_HOME="cache/gradle/$PROJECT_NAME"
  export PROJECT_IMAGE_TAG="$AWS_ECR_DSA_REPOSITORY_NAME/$PROJECT_NAME"
  export PROJECT_IMAGE_ECR_TAG="$AWS_ECR_REGISTRY_URL/$PROJECT_IMAGE_TAG"
fi
