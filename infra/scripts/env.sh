#!/bin/sh

export DSA_SCRIPTS_DIR="$(pwd)"

export DSA_ROOT_DIR="$(cd "$DSA_SCRIPTS_DIR/../.." && pwd)"
export DSA_ARTIFACTS_DIR="$DSA_ROOT_DIR/artifacts"
export DSA_INFRA_DIR="$DSA_ROOT_DIR/infra"
export DSA_SERVICES_DIR="$DSA_ROOT_DIR/services"

export SHARED_STARTER_ARTIFACT_DIR="$DSA_ARTIFACTS_DIR/shared-starter"

export ACCOUNTS_SERVICE_DIR="$DSA_SERVICES_DIR/accounts-service"
export AUTHENTICATION_SERVICE_DIR="$DSA_SERVICES_DIR/authentication-service"
export CONFIG_SERVER_DIR="$DSA_SERVICES_DIR/config-server"
export GATEWAY_DIR="$DSA_SERVICES_DIR/gateway"

export CI_DIR="$DSA_INFRA_DIR/ci"
export DOCKER_COMPOSES_DIR="$DSA_INFRA_DIR/docker-composes"
export K8S_DIR="$DSA_INFRA_DIR/k8s"
export AWS_SCRIPTS_DIR="$DSA_SCRIPTS_DIR/aws"