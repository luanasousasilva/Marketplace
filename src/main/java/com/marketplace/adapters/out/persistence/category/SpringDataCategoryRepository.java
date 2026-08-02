package com.marketplace.adapters.out.persistence.category;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
interface SpringDataCategoryRepository extends JpaRepository<CategoryJpaEntity,Long>{ List<CategoryJpaEntity> findByActiveTrueOrderByNameAsc(); }
