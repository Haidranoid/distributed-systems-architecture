# Distributed Systems Architecture

A backend engineering project that explores modern distributed systems architecture through independently managed microservices, reusable platform libraries, infrastructure automation, and container orchestration.

The platform is primarily built with Java and Spring Boot, while also incorporating ASP.NET Core services as part of a polyglot backend architecture.

Rather than focusing on business complexity, this repository serves as a reference implementation for backend architecture, distributed communication, reusable platform components, infrastructure, and CI/CD practices.

# Project Goals

- Build independently deployable microservices.
- Establish clear service boundaries through domain ownership.
- Provide reusable platform components without tightly coupling services.
- Support Java/Spring and .NET application ecosystems.
- Introduce asynchronous communication using Apache Kafka.
- Provide reproducible local environments with Docker Compose.
- Deploy microservices using Kubernetes and Kustomize.
- Standardize CI/CD pipelines across libraries and microservices.
- Keep each project independently buildable and maintainable.
- Continuously evolve the platform with distributed-systems patterns.

# Architecture

## System Architecture

The platform is composed of independently managed microservices, shared libraries, and reusable infrastructure.

The main request flow is centered around the gateway:

```text
                    +----------------------+
                    |       Clients        |
                    +----------+-----------+
                               |
                               v
                    +----------------------+
                    |       Gateway        |
                    +----------+-----------+
                               |
             +-----------------+------------------+
             |                 |                  |
             v                 v                  v
   +------------------+ +-------------+ +------------------+
   | Authentication   | |  Accounts   | |    Content       |
   |     Service      | |   Service   | |    Service       |
   +------------------+ +-------------+ +------------------+
             |
             |             asynchronous events
             +------------------------+
                                      |
                                      v
                              +---------------+
                              |     Kafka     |
                              +-------+-------+
                                      |
                                      v
                              +---------------+
                              | Audit Service |
                              +---------------+
```

The `config-server` provides centralized configuration to services that consume it.

The `media-generation-service` provides media generation capabilities within the platform.

## Microservices

| Service | Responsibility |
|---|---|
| `gateway` | Entry point for client applications and request routing. |
| `config-server` | Centralized configuration management. |
| `authentication-service` | Authentication, authorization, and identity-related responsibilities. |
| `accounts-service` | Account-related domain operations and business logic. |
| `audit-service` | Kafka event consumption and audit processing. |
| `content-service` | Content management and related business operations. |
| `media-generation-service` | Media generation workflows. |

Each microservice is maintained as an independent project with its own source code, build configuration, tests, and container definition.

## Service Communication

The architecture uses two primary communication models:

- **Synchronous communication:** HTTP/REST for request-response interactions.
- **Asynchronous communication:** Apache Kafka for event-driven communication.

This allows services to use synchronous communication when an immediate response is required while using asynchronous events for decoupled workflows.

## Event-Driven Architecture

Services can publish domain events to Kafka topics. Other services can consume those events according to their responsibilities.

The current architecture includes `audit-service` as a Kafka consumer responsible for processing events for auditing purposes.

The event-driven model also provides a foundation for adding additional consumers without requiring event producers to know which consumers depend on their events.

# Repository Organization

The repository follows a monorepo strategy while keeping every project independently managed.

There is a single Git repository, but services and libraries remain self-contained projects. Each project owns its own build configuration, dependencies, tests, and lifecycle rather than being part of a centralized root build.

This provides a shared repository for coordinated architectural changes while preserving project-level independence.

## Repository Structure

```text
.
├── infrastructure/
│   ├── docker-registry/
│   │   ├── composes/
│   │   │   ├── kafka/
│   │   │   └── microservices/
│   │   └── images/
│   │       └── gitlab-runners/
│   │           ├── dotnet/
│   │           └── java/
│   │
│   ├── gitlab-ci/
│   │   ├── bootstrap/
│   │   ├── pipelines/
│   │   └── templates/
│   │
│   └── k8s/
│       ├── base/
│       └── overlays/
│
├── libraries/
│   ├── common-core/
│   ├── shared-core/
│   ├── shared-plugin/
│   ├── shared-starter/
│   └── shared-starter-test/
│
├── microservices/
│   ├── accounts-service/
│   ├── audit-service/
│   ├── authentication-service/
│   ├── config-server/
│   ├── content-service/
│   ├── gateway/
│   └── media-generation-service/
│
├── scripts/
├── bootstrap.sh
└── README.md
```

## Directory Responsibilities

| Directory | Purpose |
|---|---|
| `infrastructure/` | Container environments, CI/CD definitions, and Kubernetes deployment configuration. |
| `libraries/` | Reusable platform libraries and build tooling. |
| `microservices/` | Independently deployable backend services. |
| `scripts/` | Repository and development automation scripts. |

# Shared Libraries

The `libraries/` directory contains reusable components for the Java/Spring and .NET ecosystems.

The naming convention identifies the target ecosystem:

- `shared-*` → Java / Spring.
- `common-*` → .NET.

This convention keeps both ecosystems under the same `libraries/` directory without requiring separate `java/` and `dotnet/` directory hierarchies.

| Library | Ecosystem | Role |
|---|---|---|
| `common-core` | .NET | Shared core library for .NET projects. |
| `shared-core` | Java / Spring | Shared core library for Java/Spring projects. |
| `shared-plugin` | Java / Gradle | Reusable Gradle plugin and build conventions. |
| `shared-starter` | Java / Spring | Shared Spring Boot starter functionality. |
| `shared-starter-test` | Java / Spring | Reusable testing support associated with the shared starter. |

## Shared Gradle Plugin

`shared-plugin` centralizes reusable Gradle configuration and build conventions for Java projects.

The purpose is to avoid duplicating common Gradle configuration across individual services and libraries while allowing each project to remain independently managed.

# Polyglot Architecture

The platform intentionally supports multiple backend ecosystems.

## Java / Spring

The primary backend ecosystem uses:

- Java
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Spring Cloud Config
- Gradle

The Java services and libraries remain independently managed Gradle projects.

## .NET

The platform also includes ASP.NET Core services and shared .NET functionality.

Current .NET projects include:

- `content-service`
- `media-generation-service`
- `common-core`

The .NET projects maintain their own solution and project configuration independently from the Java projects.

# Infrastructure

## Docker Registry and Local Containers

The `docker-registry/` directory contains local container infrastructure and custom container images used by the development and CI/CD environments.

### Docker Compose

Two Compose environments are maintained:

```text
infrastructure/docker-registry/composes/
├── kafka/
│   └── docker-compose.yml
└── microservices/
    └── docker-compose.yml
```

The Kafka Compose configuration starts the Kafka infrastructure.

The microservices Compose configuration provides a local containerized environment for running the platform's microservices.

### Custom GitLab Runner Images

Custom GitLab Runner images are maintained separately for each supported ecosystem:

```text
infrastructure/docker-registry/images/gitlab-runners/
├── dotnet/
│   ├── Dockerfile
│   └── notes.txt
└── java/
    ├── Dockerfile
    └── notes.txt
```

The runner environments target:

- Java 21
- .NET 10

These images provide controlled execution environments for GitLab CI/CD jobs.

## Kubernetes

Kubernetes is used as the container orchestration and deployment platform.

The Kubernetes configuration follows a Kustomize-based `base` and `overlay` structure.

### Base Resources

Common resources for each microservice are maintained under:

```text
infrastructure/k8s/base/microservices/
```

Each microservice has its own:

```text
deployment.yml
service.yml
kustomization.yml
```

The base layer defines the common Kubernetes resources required to deploy each service.

### Environment Overlays

Environment-specific configuration is maintained under:

```text
infrastructure/k8s/overlays/
```

The current environment is:

```text
dev-local/
├── common/
│   ├── configmap.yml
│   ├── kustomization.yml
│   └── namespace.yml
└── microservices/
    ├── accounts-service/
    ├── audit-service/
    ├── authentication-service/
    ├── config-server/
    ├── content-service/
    ├── gateway/
    └── media-generation-service/
```

This separation allows common deployment resources to remain in `base/` while environment-specific configuration is applied through overlays.

# CI/CD

The repository contains reusable GitLab CI/CD pipelines designed to standardize the lifecycle of libraries and microservices across Java and .NET.

The CI/CD configuration is organized into three layers:

```text
bootstrap/
    |
    v
pipelines/
    |
    v
templates/
```

## Bootstrap

The bootstrap layer establishes common and ecosystem-specific CI/CD configuration:

```text
infrastructure/gitlab-ci/bootstrap/
├── base.yml
├── dotnet.yml
└── java.yml
```

## Pipelines

Pipeline definitions are separated by project type and ecosystem:

```text
infrastructure/gitlab-ci/pipelines/
├── pipeline-library-dotnet.yml
├── pipeline-library-java.yml
├── pipeline-microservice-dotnet.yml
└── pipeline-microservice-java.yml
```

This allows libraries and microservices to have distinct lifecycle pipelines while sharing the same CI/CD infrastructure.

## Reusable Templates

The template layer contains reusable jobs for common lifecycle operations:

```text
infrastructure/gitlab-ci/templates/
├── deploy-library-dotnet.yml
├── deploy-library-java.yml
├── deploy-microservice-dotnet.yml
├── deploy-microservice-java.yml
├── publish-library-java.yml
├── publish-microservice-dotnet.yml
├── publish-microservice-java.yml
├── test-library-dotnet.yml
├── test-library-java.yml
├── test-microservice-dotnet.yml
└── test-microservice-java.yml
```

Templates are separated by ecosystem and project type where build, test, publish, or deployment requirements differ.

This structure keeps reusable CI/CD behavior in the infrastructure layer instead of duplicating it across individual projects.

# Technology Stack

| Area | Technologies |
|---|---|
| Languages | Java, C# |
| Backend | Spring Boot, ASP.NET Core |
| Security | Spring Security |
| API Gateway | Spring Cloud Gateway |
| Configuration | Spring Cloud Config |
| Messaging | Apache Kafka |
| Database | PostgreSQL |
| Build | Gradle, .NET CLI |
| Containers | Docker, Docker Compose |
| Orchestration | Kubernetes |
| Deployment Configuration | Kustomize |
| CI/CD | GitLab CI/CD |
| GitLab Runners | Custom Java 21 and .NET 10 images |

# Architectural Principles

## Independent Project Lifecycles

Every microservice and library is independently managed despite living in the same Git repository.

## Clear Service Boundaries

Each microservice owns a defined business or platform responsibility rather than becoming a shared application module.

## Reusable Platform Components

Common application capabilities and build behavior are extracted into reusable libraries and Gradle conventions instead of being duplicated across services.

## Polyglot Support

The architecture supports Java/Spring and .NET without forcing both ecosystems into the same project structure or build system.

## Explicit Communication

Synchronous HTTP communication and asynchronous Kafka events are treated as distinct communication mechanisms with different responsibilities.

## Infrastructure as a Shared Concern

Docker, Kubernetes, Kustomize, and GitLab CI/CD definitions are maintained as reusable infrastructure rather than being duplicated inside every application.

## Incremental Evolution

The platform is designed to evolve over time as new distributed-systems patterns and infrastructure capabilities are introduced.

# Roadmap

The repository is intended to evolve incrementally as additional distributed-systems patterns are explored.

Potential areas include:

- Service discovery.
- Advanced distributed configuration.
- OAuth2 authorization server capabilities.
- Circuit breakers and resilience patterns.
- API versioning.
- Transactional Outbox pattern.
- Saga orchestration/choreography.
- CQRS.
- Contract testing.
- Event replay.
- Dead-letter queues.
- Distributed caching.
- Secret management.
- Multi-environment deployments.
- Additional domain services.

The roadmap represents potential architectural experiments rather than guaranteed implementation commitments.
