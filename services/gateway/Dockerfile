# syntax=docker/dockerfile:1.7

# -------- Stage 1: Dependencies --------
FROM amazoncorretto:21-alpine3.23-jdk AS dependencies

# Install required tools
RUN apk add --no-cache bash curl unzip

# Accept build-time args
ARG AWS_CODEARTIFACT_AUTH_TOKEN
ENV AWS_CODEARTIFACT_AUTH_TOKEN=$AWS_CODEARTIFACT_AUTH_TOKEN

WORKDIR /app

# Copy ONLY files affecting dependency resolution
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew

# Cache Gradle dependencies
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew dependencies \
      --no-daemon

# -------- Stage 2: Build --------
FROM amazoncorretto:21-alpine3.23-jdk AS build

RUN apk add --no-cache bash curl unzip

ARG AWS_CODEARTIFACT_AUTH_TOKEN
ENV AWS_CODEARTIFACT_AUTH_TOKEN=$AWS_CODEARTIFACT_AUTH_TOKEN

WORKDIR /app

# Reuse wrapper/config
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew

# Copy source LAST
COPY src ./src

# Build application using cached Gradle home
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew bootJar \
      -x test \
      --no-daemon

# -------- Stage 3: Runtime --------
FROM eclipse-temurin:21-jre-alpine-3.23

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=dev-docker

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8222

ENTRYPOINT ["java", "-jar", "app.jar"]