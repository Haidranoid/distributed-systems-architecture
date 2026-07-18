# Distributed Systems Architecture

A backend engineering project that explores modern distributed systems
architecture through independently managed microservices, reusable
platform libraries, cloud-native infrastructure, and automated
deployment workflows.

The platform is primarily built with Spring Boot while also exploring
interoperability with ASP.NET Core and other technologies commonly found
in polyglot distributed systems.

Rather than focusing on business complexity, this repository serves as a
long-term reference implementation for backend architecture,
infrastructure, and distributed systems concepts commonly found in
production environments.

------------------------------------------------------------------------

# Project Goals

This repository explores the design and implementation of modern backend
platforms through practical engineering.

Current objectives include:

-   Build independently deployable microservices
-   Promote clear service boundaries through domain ownership
-   Share reusable infrastructure without tightly coupling services
-   Introduce asynchronous communication using Apache Kafka
-   Containerize local development environments
-   Deploy applications using Kubernetes
-   Standardize CI/CD pipelines
-   Continuously expand the platform with production-oriented
    architectural patterns

The repository evolves incrementally as new distributed systems concepts
are implemented and validated.

------------------------------------------------------------------------

# Repository Organization

The repository adopts a **monorepo strategy** while keeping every
project independently managed.

Rather than using a centralized build workspace, every service, shared
library, and infrastructure module owns its own lifecycle, build
configuration, Docker image, and deployment process.

This approach allows individual projects to evolve independently while
still benefiting from a shared Git history, coordinated cross-project
changes, and reusable platform components.

``` txt
artifacts/
│
├── java/
│   └── shared-starter/
├── net/
│   └── shared-core/
│
infra/
│
├── ci/
│   ├── bootstrap/
│   ├── runners/
│   └── templates/
├── docker-composes/
│   ├── kafka/
│   └── services/
├── k8s/
│   ├── base/
│   └── overlays/
└── scripts/
│
services/
│
├── config-server/
├── gateway/
├── authentication-service/
├── accounts-service/
├── audit-service/
├── media-generation-service/
└── content-service/
```



-----------------------------------------------------------------------
| Directory      | Purpose                                                                                                              |
|----------------|----------------------------------------------------------------------------------------------------------------------|
| **artifacts/** | Reusable platform libraries and shared components consumed across multiple services and technologies.                |
| **infra/**     | CI/CD, container orchestration, deployment manifests, infrastructure automation, and local development environments. |
| **services/**  | Independently deployable microservices implemented using Spring Boot and ASP.NET Core.                               |
-----------------------------------------------------------------------

------------------------------------------------------------------------

This separation allows platform infrastructure to evolve independently
of application code while keeping reusable components isolated.

------------------------------------------------------------------------

# Platform Overview

The platform currently consists of multiple independently deployable
services.

Some services provide platform infrastructure, while others implement
business capabilities.


-----------------------------------------------------------------------
| Service                      | Responsibility                                          |
|------------------------------|---------------------------------------------------------|
| **Gateway**                  | Single entry point for client applications.             |
| **Config Server**            | Centralized configuration management.                   |
| **Authentication Service**   | Identity management, authentication, and authorization. |
| **Accounts Service**         | Account domain and business operations.                 |
| **Audit Service**            | Asynchronous event processing and audit logging.        |
| **Media Generation Service** | Media generation workflows.                             |
| **Content Service**          | Content management and business operations.             |
-----------------------------------------------------------------------

-----------------------------------------------------------------------

Each service owns its own persistence, deployment lifecycle,
configuration, and business logic.

------------------------------------------------------------------------

# Shared Libraries

Reusable libraries are grouped under the `artifacts` directory.

These libraries provide common infrastructure that can be consumed by
multiple services while keeping business logic isolated.


-----------------------------------------------------------------------
| Artifact           | Description                                                                                                |
|--------------------|------------------------------------------------------------------------------------------------------------|
| **shared-starter** | Spring Boot shared infrastructure, messaging, common configuration, and reusable platform components.      |
| **shared-core**    | Shared .NET infrastructure intended to provide equivalent platform capabilities for ASP.NET Core services. |
-----------------------------------------------------------------------

-----------------------------------------------------------------------

The long-term objective is to provide consistent infrastructure across
technologies while allowing services to remain independently deployable.

------------------------------------------------------------------------

# Event-Driven Architecture

The platform combines synchronous and asynchronous communication
depending on the use case.

REST APIs remain appropriate for request-response interactions.

Apache Kafka is introduced for domain events that should be processed
independently by multiple services.

Examples of published domain events include account lifecycle events,
media processing events, and other business notifications exchanged
asynchronously between services.

This architecture enables services to react to business events without
creating direct runtime dependencies between them.

------------------------------------------------------------------------

# Infrastructure

Infrastructure resources are centralized under the `infra` directory.

Current platform capabilities include:

-   Docker Compose environments
-   Kubernetes manifests
-   Kustomize overlays
-   GitLab CI/CD templates
-   Development automation scripts

Infrastructure remains independent of application code, making
deployment strategies easier to evolve over time.

------------------------------------------------------------------------

# Technology Stack

-   Java
-   Spring Boot
-   ASP.NET Core
-   Spring Security
-   Spring Cloud Gateway
-   Spring Cloud Config
-   Apache Kafka
-   PostgreSQL
-   Docker
-   Kubernetes
-   Kustomize
-   GitLab CI/CD
-   Gradle
-   .NET

------------------------------------------------------------------------

# Polyglot Architecture

Although Spring Boot is currently the primary application framework, the
repository intentionally explores a polyglot architecture.

Different services may be implemented using different technologies
whenever they better demonstrate a particular architectural concept.

Shared platform components aim to provide equivalent capabilities across
ecosystems while preserving a consistent developer experience.

------------------------------------------------------------------------

# Architectural Principles

-   Services own their business domains.
-   Infrastructure remains reusable.
-   Shared libraries expose contracts instead of business logic.
-   Platform concerns remain separated from application code.
-   Components should remain independently deployable.
-   Service communication should remain explicit.
-   Architecture should evolve incrementally as new capabilities are
    introduced.

------------------------------------------------------------------------

# Roadmap

This repository is continuously evolving.

Planned areas of exploration include:

-   Service Discovery
-   Distributed Configuration
-   Polyglot Configuration Providers
-   OAuth2 Authorization Server
-   Distributed Tracing
-   Centralized Observability
-   Circuit Breakers
-   Resilience Patterns
-   API Versioning
-   Outbox Pattern
-   Saga Pattern
-   CQRS
-   Contract Testing
-   Event Replay
-   Dead Letter Queues
-   Distributed Caching
-   Secret Management
-   Multi-environment deployments
-   Additional business services

Each new component is intended to demonstrate a real architectural
concept commonly encountered in modern distributed systems.
