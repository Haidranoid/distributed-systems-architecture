# Distributed Systems Architecture

A backend engineering project that explores modern distributed systems architecture using independently managed Spring Boot microservices, event-driven communication, cloud-native infrastructure, and automated deployment workflows.

Rather than focusing on business complexity, this repository serves as a long-term reference implementation for backend architecture, infrastructure, and distributed systems concepts commonly found in production environments.

---

# Project Goals

This repository explores the design and implementation of modern backend platforms through practical engineering.

Current objectives include:

- Build independently deployable microservices
- Promote clear service boundaries through domain ownership
- Share reusable infrastructure without tightly coupling services
- Introduce asynchronous communication using Apache Kafka
- Containerize local development environments
- Deploy applications using Kubernetes
- Standardize CI/CD pipelines
- Continuously expand the platform with production-oriented architectural patterns

The repository evolves incrementally as new distributed systems concepts are implemented and validated.

---

# Repository Organization

The project adopts a **monorepo strategy** while keeping every component independently managed.

Each service, shared library, and infrastructure module owns its own build lifecycle, Gradle configuration, Docker image, and deployment process, allowing components to evolve independently without requiring a centralized workspace.

```txt
artifacts/
│
├── shared-starter/
│
infra/
│
├── ci/
├── docker-composes/
├── k8s/
└── scripts/
│
services/
│
├── authentication-service/
├── accounts-service/
├── audit-service/
├── config-server/
└── gateway/
```

Repository responsibilities are intentionally separated:

| Directory | Purpose |
|-----------|---------|
| **services/** | Independently deployable Spring Boot microservices. |
| **artifacts/** | Shared libraries consumed across multiple services. |
| **infra/** | Infrastructure, deployment manifests, CI/CD, Docker Compose environments, and automation scripts. |

This separation allows platform infrastructure to evolve independently from application code while keeping reusable components isolated.

---

# Platform Overview

Current services include:

| Service | Responsibility |
|----------|----------------|
| Authentication Service | Authentication, authorization and identity management. |
| Accounts Service | Account domain and business operations. |
| Audit Service | Event consumers responsible for asynchronous processing. |
| Config Server | Centralized configuration management. |
| Gateway | Single entry point for client requests. |

Each service owns its own persistence, configuration, deployment lifecycle, and business logic.

---

# Shared Libraries

The repository currently provides a reusable platform library:

## shared-starter

The shared starter centralizes infrastructure that should remain consistent across services.

Examples include:

- Shared event contracts
- Kafka topics
- Common DTOs
- Messaging abstractions
- Shared utilities
- Cross-service infrastructure

The objective is to reduce duplication without introducing unnecessary coupling between service implementations.

---

# Event-Driven Architecture

The platform combines synchronous and asynchronous communication depending on the use case.

REST APIs remain appropriate for request-response interactions.

Apache Kafka is introduced for domain events that should be processed independently by multiple services.

Current events include:

- Account Created
- Account Updated
- Password Changed

This architecture enables services to react to business events without creating direct runtime dependencies between them.

---

# Infrastructure

Infrastructure resources are centralized under the `infra` directory.

Current platform capabilities include:

- Docker Compose environments
- Kubernetes manifests
- Kustomize overlays
- GitLab CI/CD templates
- Development automation scripts

Infrastructure remains independent from application code, making deployment strategies easier to evolve over time.

---

# Technology Stack

The repository currently explores technologies including:

- Java
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Spring Cloud Config
- Apache Kafka
- PostgreSQL
- Docker
- Kubernetes
- Kustomize
- GitLab CI/CD
- Gradle

The technology stack continues expanding as additional distributed systems patterns are incorporated.

---

# Architectural Principles

Several engineering principles guide the repository:

- Services own their business domains.
- Infrastructure remains reusable.
- Shared libraries expose contracts instead of business logic.
- Platform concerns remain separated from application code.
- Components should remain independently deployable.
- Service communication should remain explicit.
- Architecture should evolve incrementally as new capabilities are introduced.

---

# Roadmap

This repository is continuously evolving.

Future areas of exploration include:

- Service Discovery
- Distributed Tracing
- Centralized Observability
- Circuit Breakers
- Resilience Patterns
- API Versioning
- Outbox Pattern
- Saga Pattern
- Event Replay
- Dead Letter Queues
- Multi-environment deployments
- Additional business services

Each new component is intended to demonstrate a real architectural concept commonly encountered in modern distributed systems.