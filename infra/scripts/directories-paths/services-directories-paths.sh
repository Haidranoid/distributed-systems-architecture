#!/bin/sh

set -eu

export ACCOUNTS_SERVICE_DIR="$SERVICES_DIR/accounts-service"
export AUTHENTICATION_SERVICE_DIR="$SERVICES_DIR/authentication-service"
export CONFIG_SERVER_DIR="$SERVICES_DIR/config-server"
export GATEWAY_DIR="$SERVICES_DIR/gateway"