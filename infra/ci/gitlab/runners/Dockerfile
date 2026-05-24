FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache \
    aws-cli \
    docker-cli \
    docker-cli-buildx \
    git \
    jq \
    curl

COPY --from=bitnami/kubectl:latest \
    /opt/bitnami/kubectl/bin/kubectl \
    /usr/local/bin/kubectl