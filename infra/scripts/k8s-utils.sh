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
  kubectl rollout restart deployment -n dev-k8s accounts-service
  kubectl rollout restart deployment -n dev-k8s authentication-service
  kubectl rollout restart deployment -n dev-k8s config-server
  kubectl rollout restart deployment -n dev-k8s gateway

  kubectl rollout status deployment/accounts-service -n dev-k8s
  kubectl rollout status deployment/authentication-service -n dev-k8s
  kubectl rollout status deployment/config-server -n dev-k8s
  kubectl rollout status deployment/gateway -n dev-k8s
}

restart_deployments_v2() {
  kubectl rollout restart deployment -n dev-k8s
  kubectl rollout status deployment -n dev-k8s
}

wait_for_image() {
  image="$1"

  until sudo k3s ctr images ls | grep -q "$image"; do
    echo "Waiting for image: $image"
    sleep 1
  done

  echo "Image available: $image"
}