#!/bin/sh

set -eu

export CI_DIR="$INFRA_DIR/ci"
export DOCKER_COMPOSES_DIR="$INFRA_DIR/docker-composes"
export K8S_DIR="$INFRA_DIR/k8s"
export AWS_SCRIPTS_DIR="$SCRIPTS_DIR/aws"