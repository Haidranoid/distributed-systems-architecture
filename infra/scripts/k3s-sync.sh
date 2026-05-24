#!/bin/sh

set -eu

wait_for_image() {
  image="$1"

  until sudo k3s ctr images ls | grep -q "$image"; do
    echo "Waiting for image: $image"
    sleep 1
  done

  echo "Image available: $image"
}

echo "Importing Docker images into k3s containerd..."

docker save $(docker images --format "{{.Repository}}:{{.Tag}}" | grep '^dsa/') \
  | sudo k3s ctr images import -

echo "Waiting for containerd image registration..."

wait_for_image "dsa/config-server"
wait_for_image "dsa/accounts-service"
wait_for_image "dsa/authentication-service"
wait_for_image "dsa/gateway"

echo "Restarting deployments..."

kubectl rollout restart deployment/config-server -n dev-k8s
kubectl rollout restart deployment/accounts-service -n dev-k8s
kubectl rollout restart deployment/authentication-service -n dev-k8s
kubectl rollout restart deployment/gateway -n dev-k8s

echo "Waiting for rollouts..."

kubectl rollout status deployment/config-server -n dev-k8s --timeout=180s
kubectl rollout status deployment/accounts-service -n dev-k8s --timeout=180s
kubectl rollout status deployment/authentication-service -n dev-k8s --timeout=180s
kubectl rollout status deployment/gateway -n dev-k8s --timeout=180s

echo "All deployments updated successfully."