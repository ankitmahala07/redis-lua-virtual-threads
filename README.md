# Redis Lua + Java Virtual Threads Event System

A production-grade event processing system built with:

- Redis Lua for atomic coordination
- Java 24 Virtual Threads (Project Loom)
- Spring Boot 4.x
- Redis ZSET-based scheduling with visibility timeouts

This project demonstrates how to build a fast, scalable, and crash-safe backend without distributed locks or reactive complexity.

---

## Architecture
CREATE → SCHEDULED → CLAIMED → COMPLETE
↘︎ TIMEOUT ↘︎ RETRY

Redis is the source of truth.

---

## Tech Stack

- Java 24
- Spring Boot 4.0.1
- Redis (auth-enabled)
- Lettuce
- Lua scripting
- Virtual Threads

---

## Redis Data Model

| Purpose | Key | Type |
|------|----|----|
| Scheduled | events:scheduled | ZSET |
| Processing | events:processing | ZSET |
| Payload | event:{id} | HASH |

---

## Running Locally

### Prerequisites

- Java 24
- Redis 6+
- Maven 3.9+

---

### Redis Setup

```bash
redis-server --requirepass YOUR_PASSWORD
