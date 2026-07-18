FROM docker:29.2.1-cli AS docker-cli

FROM eclipse-temurin:21-jdk-alpine

COPY --from=docker-cli /usr/local/bin/docker /usr/local/bin/docker
COPY --from=docker-cli /usr/local/libexec/docker/cli-plugins /usr/local/libexec/docker/cli-plugins

RUN apk add --no-cache \
    aws-cli \
    git \
    jq \
    curl \
    gettext

COPY --from=bitnami/kubectl:latest \
    /opt/bitnami/kubectl/bin/kubectl \
    /usr/local/bin/kubectl