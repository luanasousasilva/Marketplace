package com.marketplace.adapters.out.persistence.product;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, Long> {
    Page<ProductJpaEntity> findByActiveTrue(Pageable pageable);
    Page<ProductJpaEntity> findByActiveTrueAndNameContainingIgnoreCase(String name, Pageable pageable);
    long countByActiveTrue(); long countByActiveTrueAndNameContainingIgnoreCase(String name);
}
