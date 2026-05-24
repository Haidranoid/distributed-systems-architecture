#!/bin/sh

set -eu

k3s_sync_images() {
  sync_version="${1:-}"

  if [ "$sync_version" = "v1" ]; then
    docker save dsa/config-server:latest | sudo k3s ctr images import -
    docker save dsa/accounts-service:latest | sudo k3s ctr images import -
    docker save dsa/authentication-service:latest | sudo k3s ctr images import -
    docker save dsa/gateway:latest | sudo k3s ctr images import -

    restart_deployments_v1
  else
    docker save $(docker images --format "{{.Repository}}:{{.Tag}}" | grep '^dsa/') \
      | sudo k3s ctr images import -

    restart_deployments_v2
  fi
}

restart_deployments_v1() {
  kubectl rollout restart deployment -n k8s-local accounts-service
  kubectl rollout restart deployment -n k8s-local authentication-service
  kubectl rollout restart deployment -n k8s-local config-server
  kubectl rollout restart deployment -n k8s-local gateway

  kubectl rollout status deployment/accounts-service -n k8s-local
  kubectl rollout status deployment/authentication-service -n k8s-local
  kubectl rollout status deployment/config-server -n k8s-local
  kubectl rollout status deployment/gateway -n k8s-local
}

restart_deployments_v2() {
  kubectl rollout restart deployment -n k8s-local
  kubectl rollout status deployment -n k8s-local
}

wait_for_image() {
  image="$1"

  until sudo k3s ctr images ls | grep -q "$image"; do
    echo "Waiting for image: $image"
    sleep 1
  done

  echo "Image available: $image"
}