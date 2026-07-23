FROM docker:29.2.1-cli AS docker-cli

FROM mcr.microsoft.com/dotnet/sdk:10.0

# Prevent interactive prompts during installation
ENV DEBIAN_FRONTEND=noninteractive

COPY --from=docker-cli /usr/local/bin/docker /usr/local/bin/docker
COPY --from=docker-cli /usr/local/libexec/docker/cli-plugins /usr/local/libexec/docker/cli-plugins

#RUN apt-get update && apt-get install -y --no-install-recommends \
RUN apt-get update && apt-get install -y \
      git \
      curl \
      unzip \
      jq \
      gettext \
      && curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" \
      && unzip awscliv2.zip \
      && ./aws/install \
      && rm -rf awscliv2.zip ./aws /var/lib/apt/lists/*

COPY --from=bitnami/kubectl:latest \
    /opt/bitnami/kubectl/bin/kubectl \
    /usr/local/bin/kubectl

# Verify the installation
RUN docker version || true
RUN docker buildx version
RUN kubectl version --client
RUN dotnet --version
RUN aws --version