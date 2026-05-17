# -------- Stage 1: Dependencies --------
FROM amazoncorretto:21-alpine3.23-jdk AS dependencies

# Accept build-time args and pass them to env vars
ARG CODEARTIFACT_AUTH_TOKEN
ENV CODEARTIFACT_AUTH_TOKEN=$CODEARTIFACT_AUTH_TOKEN

# Create working directory
WORKDIR /app

# Copy Gradle wrapper and build config
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Make wrapper executable
RUN chmod +x gradlew

# Pre-download/cache dependencies
RUN ./gradlew assemble \
    -x test \
    --no-daemon || true


# -------- Stage 2: Build --------
FROM amazoncorretto:21-alpine3.23-jdk AS build

# Accept build-time args and pass them to env vars
ARG CODEARTIFACT_AUTH_TOKEN
ENV CODEARTIFACT_AUTH_TOKEN=$CODEARTIFACT_AUTH_TOKEN

WORKDIR /app

# Reuse cached Gradle artifacts/wrapper
COPY --from=dependencies /root/.gradle /root/.gradle
COPY --from=dependencies /app /app

# Copy source code
COPY src ./src

# Build application
RUN ./gradlew bootJar \
    -x test \
    --no-daemon


# -------- Stage 3: Runtime --------
FROM eclipse-temurin:21-jre-alpine-3.23

# Create working directory
WORKDIR /app

# Runtime profile
ENV SPRING_PROFILES_ACTIVE=dev-docker

# Copy generated jar
COPY --from=build /app/build/libs/*.jar app.jar

# Expose application port
EXPOSE 8081

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]