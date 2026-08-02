package com.marketplace.adapters.out.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
interface SpringDataOrderRepository extends JpaRepository<OrderJpaEntity, Long> { }
