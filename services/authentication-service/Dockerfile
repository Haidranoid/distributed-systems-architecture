# -------- Stage 1: Build --------
FROM eclipse-temurin:21-jdk-jammy AS build

# Accept optional build-time args
ARG CODEARTIFACT_AUTH_TOKEN

# Pass env vars to build-time
ENV CODEARTIFACT_AUTH_TOKEN=$CODEARTIFACT_AUTH_TOKEN

# Create working directory
WORKDIR /app

# Copy wrapper + gradle config first
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Make wrapper executable
RUN chmod +x gradlew

# Download dependencies (cache layer)
RUN ./gradlew classes -x test --no-daemon || true

# Copy source code
COPY src ./src

# Build
RUN ./gradlew bootJar -x test --no-daemon

# -------- Stage 2: Runtime --------
FROM eclipse-temurin:21-jre-jammy

# Create working directory
WORKDIR /app

# Pass env vars to runtime
ENV SPRING_PROFILES_ACTIVE=dev-docker

# Copy JAR from build stage
COPY --from=build /app/build/libs/authentication-service.jar app.jar

# Expose the app port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]