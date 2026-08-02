package com.marketplace.productservice;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
interface StockReservationRepository extends JpaRepository<StockReservation,Long>{Optional<StockReservation> findByIdempotencyKey(String key);}
