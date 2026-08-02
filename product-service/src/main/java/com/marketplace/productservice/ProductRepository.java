package com.marketplace.productservice;
import jakarta.persistence.LockModeType; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.util.*;
public interface ProductRepository extends JpaRepository<Product,Long>{ Page<Product> findByNameContainingIgnoreCase(String query,Pageable pageable); @Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select p from Product p where p.id=:id") Optional<Product> findByIdForUpdate(@Param("id") Long id); }
