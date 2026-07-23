FROM amazon/aws-cli:2.36.7 AS aws-cli

FROM docker:29.2.1-cli AS docker-cli

FROM mcr.microsoft.com/dotnet/sdk:10.0

COPY --from=docker-cli /usr/local/bin/docker /usr/local/bin/docker
COPY --from=docker-cli /usr/local/libexec/docker/cli-plugins /usr/local/libexec/docker/cli-plugins

COPY --from=aws-cli /usr/local/aws-cli /usr/local/aws-cli
COPY --from=aws-cli /usr/local/bin/aws /usr/local/bin/aws

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        git \
        jq \
        curl \
        gettext-base && \
    rm -rf /var/lib/apt/lists/*

COPY --from=bitnami/kubectl:latest \
    /opt/bitnami/kubectl/bin/kubectl \
    /usr/local/bin/kubectl