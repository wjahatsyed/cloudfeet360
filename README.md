# CloudFleet360

CloudFleet360 is a modular, distributed fleet and dispatch management platform built using Spring Boot, Java 17, and cloud-native technologies. It demonstrates production-grade backend engineering across microservices, DevOps, security, and observability.

## 🌐 Overview

The system manages users, vehicles, dispatch logic, notifications, and analytics through loosely coupled microservices. It uses event-driven communication (Kafka & RabbitMQ) and implements JWT-based authentication, observability, and CI/CD pipelines.

---

## 📦 Architecture

- **Microservices**: Deployed independently, communicate via REST or messaging
- **Event-Driven Design**: Kafka (dispatch events), RabbitMQ (notifications)
- **Security**: JWT, Spring Security, role-based access control (RBAC)
- **API Gateway**: Spring Cloud Gateway for routing and security
- **Cloud-Native**: Docker, Kubernetes manifests for AWS, GCP, OCI, Azure
- **Observability**: Prometheus, Grafana, Spring Actuator
- **DevOps**: GitHub Actions CI/CD, Dockerized builds, Helm (optional)

---

## 🧩 Services

| Service              | Port | Responsibilities                                              |
|----------------------|------|---------------------------------------------------------------|
| auth-service         | 8081 | JWT login, registration, RBAC authentication                 |
| fleet-service        | 8082 | Manages vehicles and statuses                                |
| dispatch-service     | 8083 | Dispatch logic, Kafka producer                               |
| analytics-service    | 8084 | Metrics tracking, Prometheus integration                     |
| notification-service | 8085 | Consumes events, sends emails/SMS via RabbitMQ              |
| api-gateway          | 8060 | Unified entrypoint, routes + validates JWT tokens            |

---

## 🔐 Authentication

- Login/Registration using JWT tokens
- Token included as `Authorization: Bearer <token>` in HTTP headers
- Role-based access control via Spring Security
- API Gateway filters unauthorized access

---

## 🚀 DevOps & CI/CD

- **GitHub Actions**: Build, test, and dockerize each service
- **Docker**: Dockerfiles in each service, docker-compose for local dev
- **Kubernetes**: Manifests in `/infra/k8s/` for deployment on cloud providers
- **Observability**: Prometheus scraping, Grafana dashboards via preconfigured YAML
- **Logging**: Centralized structured logs via SLF4J and logback
- **Helm** (optional): For templated deployments

---

## ⚙️ Tech Stack

- **Languages**: Java 17
- **Frameworks**: Spring Boot 3.x, Spring Cloud
- **Messaging**: Apache Kafka, RabbitMQ
- **Database**: PostgreSQL (via Spring Data JPA)
- **Security**: Spring Security + JWT
- **Build**: Gradle
- **CI/CD**: GitHub Actions, Docker
- **Observability**: Spring Actuator, Prometheus, Grafana

---

## 🛠️ Local Development

1. Clone the repository
2. Start dependent services with:
   ```bash
   docker-compose up -d
Run each Spring Boot app using your IDE or CLI:

bash
Copy
Edit
./mvnw spring-boot:run
Access Swagger UI for each service at:

http://localhost:8081/swagger-ui.html (auth)

http://localhost:8082/swagger-ui.html (fleet)

... etc.

✨ Contribution Guidelines
Follow naming conventions for classes and packages

Write unit/integration tests (80%+ coverage encouraged)

Use feature branches for PRs

Document ADRs in /docs/architecture/

Raise issues with clear reproduction steps and logs

📈 Future Enhancements
Admin dashboard (React)

OpenTelemetry integration

API rate limiting

Redis caching layer

Canary deployments using Argo Rollouts
