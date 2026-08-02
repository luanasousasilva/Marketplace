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

## Recursos de produção

- **Comunicação síncrona:** `order-service` usa OpenFeign para solicitar uma reserva ao `product-service` antes de confirmar o pedido.
- **Estoque consistente:** `Product` possui `@Version` para lock otimista; a operação de reserva usa consulta com `PESSIMISTIC_WRITE`. Uma chave `Idempotency-Key` é persistida em `stock_reservations`, tornando reenvios seguros.
- **Contrato da API:** DTOs de requisição/resposta, Bean Validation, MapStruct e respostas de erro padronizadas no serviço de Produtos.
- **Pesquisa:** `GET /api/products?q=mouse&page=0&size=20&sort=name,asc` e `GET /api/orders?customerId=1&page=0&size=20`.
- **Operação:** Swagger UI (`/swagger-ui/index.html`) e Actuator (`/actuator/health`, `/actuator/metrics`) nos serviços de Produtos e Pedidos. O cache distribuído e as sessões usam Redis.

### Segurança

Os serviços devem operar atrás de um provedor OAuth2/OIDC que emita JWTs. O contrato recomendado é propagar `Authorization: Bearer <token>` nas chamadas OpenFeign e autorizar escopos/roles no gateway e em cada serviço. Segredos, URLs de bancos e chaves JWT devem ser fornecidos por variáveis de ambiente ou um cofre de segredos — nunca versionados.

### Consistência entre serviços

Não se utiliza two-phase commit entre PostgreSQL, MongoDB e serviços HTTP. O fluxo segue uma **Saga**: reserva idempotente de estoque, criação do pedido e ações compensatórias (liberar reserva) quando uma etapa falha. Para entrega garantida de eventos, acrescente um Outbox no PostgreSQL e um publicador para Kafka/RabbitMQ; é o modelo preparado para escalar sem transações distribuídas frágeis.

### Testes

As dependências de teste JUnit 5 e Testcontainers PostgreSQL estão configuradas nos serviços de Produtos e Pedidos. Para testes de integração, suba os bancos via Testcontainers em vez de depender de serviços locais; para regras puras, use JUnit/Mockito.

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
