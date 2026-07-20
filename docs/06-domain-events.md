# 06. Domain Events

# Overview

Services communicate asynchronously using Domain Events.

RabbitMQ is responsible for delivering events between services.

---

# Why Events?

Events reduce coupling between services.

Instead of calling another service directly, a service publishes an event describing what happened.

---

# Event Flow

Listing Created

↓

Media Service

↓

Notification Service

↓

Search Service

---

Payment Completed

↓

Promotion Service

↓

Notification Service

↓

Analytics

---

# Event Naming

Past tense.

Examples:

ListingCreatedEvent

ListingUpdatedEvent

ListingDeletedEvent

PaymentCompletedEvent

PaymentFailedEvent

PromotionActivatedEvent

UserRegisteredEvent

---

# Event Reliability

The project implements:

- Transactional Outbox Pattern
- Inbox Pattern
- Publisher Confirms
- Retry Queue
- Dead Letter Queue
- Manual ACK/NACK
- Idempotent Consumers

---

# Event Delivery

Producer

↓

Outbox Table

↓

RabbitMQ

↓

Consumer

↓

Inbox Table

↓

Business Logic

---

# Event Principles

Events represent facts.

Events are immutable.

Events are versionable.

Consumers must be idempotent.

Business logic should never depend on delivery order.