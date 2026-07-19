# TechStore Microservices

A production-inspired electronics e-commerce platform built with **Java 21** and **Spring Boot** using a **Microservices Architecture**.

The purpose of this project is to gain hands-on experience with building scalable, event-driven, production-ready backend systems while applying modern software architecture principles and best practices.

> 🚧 This project is actively under development. New microservices, patterns, and production-ready features are continuously being added.

---

# Architecture

The application follows a Microservices Architecture where each service has its own responsibility, database, and deployment lifecycle.

Current services:

- API Gateway
- User/Auth Service
- Product Service
- Cart Service
- Order Service
- Payment Service
- Wallet Service

---

# Technology Stack

## Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA (Hibernate)

## Database

- PostgreSQL
- Redis

## Messaging

- RabbitMQ

## DevOps

- Docker
- Kubernetes
- GitHub Actions

## Database Migration

- Liquibase

## Authentication

- JWT

## Mapping

- MapStruct

## Documentation

- Swagger / OpenAPI

---

# Architecture & Design Patterns

This project focuses on implementing production-ready architecture patterns rather than only CRUD operations.

Implemented patterns include:

- Microservices Architecture
- Event-Driven Architecture (EDA)
- Domain-Driven Design (DDD) Principles
- Saga Pattern
- Transactional Outbox Pattern
- Inbox Pattern
- Publisher Confirms
- Idempotent Consumers
- Optimistic Locking
- CQRS-style Commands & Events

---

# Messaging & Reliability

RabbitMQ is used for asynchronous communication between microservices.

Implemented messaging features:

- Topic Exchange
- Routing Keys
- Retry Queue
- Dead Letter Queue (DLQ)
- Publisher Confirms
- Manual ACK / NACK Management
- Correlation ID Propagation
- Event Versioning
- Retry Mechanism
- Idempotent Event Processing

---

# Transaction Consistency

To guarantee consistency between distributed services the project implements:

- Saga Pattern
- Transactional Outbox
- Inbox Pattern
- Atomic Database Transactions
- Eventual Consistency

---

# Concurrency

Concurrent processing is handled using:

- Optimistic Locking (@Version)
- FOR UPDATE SKIP LOCKED
- Transaction Isolation
- Concurrent Outbox Processing

---

# Domain-Driven Design (DDD)

The project follows practical Domain-Driven Design principles.

Examples include:

- Rich Domain Entities
- Business Logic inside Entities
- Thin Application Services
- Aggregate-oriented Design
- Encapsulated Domain Behavior

---

# Current Features

- JWT Authentication
- Product Catalog
- Shopping Cart
- Order Management
- Payment Processing
- Wallet Management
- Asynchronous Event Processing
- Database Versioning
- Dockerized Services
- Kubernetes Deployment

---

# Project Structure

```
Techstore-microservices

├── api-gateway
├── user-service
├── product-service
├── cart-service
├── order-service
├── payment-service
└── wallet-service
```

---

# Roadmap

## Core Services

- [x] API Gateway
- [x] User/Auth Service
- [x] Product Service
- [x] Cart Service
- [x] Order Service
- [x] Payment Service
- [x] Wallet Service

## Planned Services

- [ ] Inventory Service
- [ ] Notification Service

## Planned Production Features

- [ ] Resilience4j
- [ ] Circuit Breaker
- [ ] Retry
- [ ] Rate Limiter
- [ ] Bulkhead
- [ ] Redis Distributed Cache
- [ ] OpenTelemetry
- [ ] Zipkin
- [ ] Prometheus
- [ ] Grafana
- [ ] ELK Stack
- [ ] Distributed Tracing
- [ ] Centralized Logging
- [ ] API Rate Limiting
- [ ] Helm
- [ ] Kubernetes Autoscaling
- [ ] Testcontainers
- [ ] Integration Testing

---

# Goals

The primary goal of this project is not only to build an e-commerce platform, but to understand how production-grade backend systems are designed, developed, deployed, and maintained.

The project is continuously evolving by incorporating modern software architecture patterns and cloud-native technologies.

---

# Author

**Amin Ramazanov**

Backend Java Developer

GitHub: https://github.com/AminRamazanov
