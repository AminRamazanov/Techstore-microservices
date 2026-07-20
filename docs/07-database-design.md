# 07. Database Design

# Philosophy

Each microservice owns its own database.

No service may directly access another service's database.

---

# Database Per Service

Identity DB

Listing DB

Category DB

Media DB

Payment DB

Promotion DB

---

# Entity Principles

Entities contain business identity.

Value Objects contain immutable values.

Aggregates enforce business rules.

Repositories load Aggregates.

---

# Concurrency

Optimistic Locking

FOR UPDATE SKIP LOCKED

Transaction Isolation

---

# Data Consistency

Transactional Outbox

Inbox Pattern

Saga Pattern

Eventual Consistency

---

# Database Evolution

Liquibase is responsible for schema migrations.

Schema changes are versioned.

Production databases are never modified manually.

---

# Future Improvements

Read Replicas

Partitioning

Archiving

Full Text Search

Caching