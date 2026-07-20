# 05. Microservice Architecture

# Overview

AlSat follows a Microservices Architecture where each service owns a single business capability.

Each service:

- Owns its own database
- Can be deployed independently
- Can be scaled independently
- Communicates using REST and asynchronous events

---

# Architecture Diagram

                    +------------------+
                    |    API Gateway   |
                    +--------+---------+
                             |
     -----------------------------------------------------
     |           |            |          |               |
     |           |            |          |               |
+-----------+ +-----------+ +----------+ +----------+ +-------------+
| Identity  | | Listing   | | Category | |  Media   | |   Search    |
|  Service  | |  Service  | | Service  | | Service  | |   Service   |
+-----------+ +-----------+ +----------+ +----------+ +-------------+
|              |             |            |
|              |             |            |
|              +-------------+------------+
|                            |
|                      RabbitMQ
|                            |
|        ---------------------------------------
|        |              |              |        |
|        |              |              |        |
+-------------+ +-------------+ +----------------+ +----------------+
| Payment     | | Promotion   | | Notification  | | Recommendation |
| Service     | | Service     | | Service       | | Service (Future)|
+-------------+ +-------------+ +----------------+ +----------------+

---

# Communication

Synchronous

Used for:

- Authentication
- Listing CRUD
- Category queries
- Media upload

Protocol:

REST API

---

Asynchronous

Used for:

- Payments
- Notifications
- Promotions
- Analytics
- Future integrations

Protocol:

RabbitMQ

---

# Service Ownership

Identity Service

Owns:

- Users
- JWT
- Authentication
- Roles
- Permissions

---

Listing Service

Owns:

- Listings
- Listing Status
- Description
- Price
- Location

---

Category Service

Owns:

- Category hierarchy

---

Media Service

Owns:

- Images
- Videos
- File Metadata

---

Payment Service

Owns:

- Transactions
- Payment History

---

Promotion Service

Owns:

- VIP
- Featured Listings

---

Notification Service

Owns:

- Email
- SMS
- Push Notifications

---

Search Service

Owns:

- Search Index
- Filters
- Sorting

---

# Design Principles

Every service owns exactly one business capability.

No shared database.

No direct table access.

Communication happens through APIs or Domain Events.

Services can evolve independently.

---

# Future Expansion

The architecture allows adding new services without changing existing ones.

Examples:

- Chat Service
- Recommendation Service
- Moderation Service
- Analytics Service
- Audit Service