# 04. Bounded Contexts

This document defines the ownership boundaries of each domain.

Every business capability belongs to exactly one bounded context.

---

## Identity Context

Owns:

- User
- Authentication
- Authorization
- JWT
- Roles
- Permissions

Never owns:

- Listings
- Payments
- Images

---

## Listing Context

Owns:

- Listing
- Price
- Description
- Status
- Seller Reference
- Category Reference

Never owns:

- Images
- Users
- Payments

---

## Media Context

Owns:

- Images
- Videos
- File Metadata

Never owns:

- Listing business logic
- Users

Media only stores references to Listing IDs.

---

## Category Context

Owns:

- Category Tree
- Category Attributes

Never owns:

- Listings

---

## Promotion Context

Owns:

- VIP
- Premium
- Featured Listings

Never owns:

- Payments

Promotion is activated only after successful payment.

---

## Payment Context

Owns:

- Transactions
- Payment Status
- Payment History

Never owns:

- Listings
- Users

Payment only publishes domain events.

---

## Notification Context

Owns:

- Email
- SMS
- Push Notifications

Consumes events from other services.

---

# Why Bounded Context?

Every service owns its own business rules.

No service directly manipulates another service's database.

Communication happens through APIs or asynchronous events.

This allows independent deployment, scaling, and evolution of each service.