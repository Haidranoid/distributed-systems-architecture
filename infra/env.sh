#!/usr/bin/env bash

export INFRA_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

export CI_DIR="$INFRA_DIR/ci"
export DOCKER_COMPOSES_DIR="$INFRA_DIR/docker-composes"
export K8S_DIR="$INFRA_DIR/k8s"
export SCRIPTS_DIR="$INFRA_DIR/scripts"
export AWS_SCRIPTS_DIR="$SCRIPTS_DIR/aws"

export DSA_ROOT_DIR="$INFRA_DIR/../.."
export DSA_SERVICES_DIR="$DSA_ROOT_DIR/services"

export ACCOUNTS_SERVICE_DIR="$DSA_SERVICES_DIR/accounts-service"
export AUTHENTICATION_SERVICE_DIR="$DSA_SERVICES_DIR/accounts-service"
export CONFIG_SERVER_DIR="$DSA_SERVICES_DIR/accounts-service"
export GATEWAY_DIR="$DSA_SERVICES_DIR/gateway"
export SERVICES_STARTER_DIR="$DSA_SERVICES_DIR/services-starter"
