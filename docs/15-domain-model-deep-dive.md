# 15. Domain Model Deep Dive

## Purpose

This document defines the core domain model of AlSat.

The goal is to build a rich domain model where business rules live inside the domain instead of application services.

The design follows Domain-Driven Design (DDD) principles.

---

# Core Aggregate

The central business concept of the platform is a Listing.

A Listing represents a product advertisement created by a seller.

It is not a Product.

Multiple listings may exist for the same product.

Example:

iPhone 15 Pro

↓

Seller A

↓

1200 AZN

↓

Baku

↓

Used

----------------------------

iPhone 15 Pro

↓

Seller B

↓

1350 AZN

↓

Ganja

↓

Like New

These are two different Listings.

---

# Listing Aggregate

Aggregate Root

Listing

Contains

- Title
- Description
- Price
- Condition
- Status
- CategoryId
- SellerId
- Location
- CreatedAt
- UpdatedAt

---

# Value Objects

Money

Fields

- amount
- currency

Money has no identity.

Money is immutable.

---

Location

Fields

- city
- district

Future

- latitude
- longitude

Location is immutable.

---

# Entities

Listing

Owns the business lifecycle.

Media

Represents uploaded images.

Category

Represents product classification.

User

Represents platform identity.

Payment

Represents premium purchases.

Promotion

Represents VIP or Featured listings.

---

# Aggregate Ownership

Listing owns

- Title
- Description
- Money
- Status
- Condition
- Location

Listing does NOT own

- User
- Payment
- Promotion

Those belong to other bounded contexts.

---

# Business Rules

A listing must have a title.

A listing must have a price.

A listing belongs to exactly one seller.

A listing belongs to exactly one category.

A listing must contain at least one image before publication.

A sold listing cannot be edited.

Archived listings cannot be published again.

---

# Listing Lifecycle

Draft

↓

Published

↓

Sold

↓

Archived

Transitions

Draft → Published

Published → Sold

Published → Archived

Sold → Archived

Invalid transitions are rejected by the domain.

---

# Rich Domain Model

Business behavior belongs inside the aggregate.

Examples

publish()

archive()

markAsSold()

changePrice()

updateDescription()

changeLocation()

activate()

deactivate()

The aggregate protects its own invariants.

---

# Domain Events

ListingCreatedEvent

ListingUpdatedEvent

ListingPublishedEvent

ListingArchivedEvent

ListingSoldEvent

PriceChangedEvent

---

# Future Extensions

Favorites

View Counter

Reports

Moderation

AI Recommendation

Boost Score

Promotion

Statistics

---

# Design Principles

No Anemic Domain Model.

Business rules belong inside aggregates.

Entities protect their own consistency.

Value Objects are immutable.

Communication between bounded contexts happens through APIs or Domain Events.