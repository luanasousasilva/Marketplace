# Marketplace Platform — Microsserviços

Plataforma Java 21/Spring Boot 3 dividida em serviços independentes, com persistência poliglota e cache distribuído.

| Serviço | Porta | PostgreSQL | MongoDB | Redis |
| --- | ---: | --- | --- | --- |
| `customer-service` | 8081 | clientes | — | cache e sessão |
| `product-service` | 8082 | produtos | avaliações | cache e sessão |
| `order-service` | 8083 | pedidos | histórico de pedidos | cache e sessão |
| `payment-service` | 8084 | pagamentos | auditoria | cache e sessão |
| `notification-service` | 8085 | notificações | logs de entrega | cache e sessão |

## Multi-database

Cada serviço é proprietário do seu schema no PostgreSQL, preservando isolamento de dados transacionais. MongoDB guarda documentos de alto volume e consulta por linha do tempo: `order_history`, `payment_audit` e `notification_logs`. Redis mantém o cache dos recursos mais consultados (`@Cacheable`/`@CacheEvict`) e as sessões HTTP (`spring-session-data-redis`).

As escritas relacionais e de auditoria são tratadas dentro dos serviços. Em produção, a evolução indicada para publicação confiável entre bancos e serviços é Transactional Outbox + um broker (Kafka/RabbitMQ), pois não há transação ACID distribuída entre PostgreSQL e MongoDB.

## Iniciar o ambiente

```bash
docker compose up --build
```

O Compose sobe PostgreSQL, MongoDB, Redis e os cinco microsserviços. O arquivo `infra/postgres/init.sql` cria schemas isolados por serviço.

## Principais APIs

- `POST /api/customers` — `customer-service`
- `POST /api/products` — `product-service`
- `POST /api/orders` e `GET /api/orders/{id}/history` — `order-service`
- `POST /api/payments` e `POST /api/payments/{id}/confirm` — `payment-service`
- `POST /api/notifications` e `POST /api/notifications/{id}/send` — `notification-service`

Para compilar todos os serviços:

```bash
mvn verify
```
