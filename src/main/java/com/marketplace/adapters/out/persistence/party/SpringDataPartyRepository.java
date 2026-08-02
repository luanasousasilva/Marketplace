package com.marketplace.adapters.out.persistence.party;
import com.marketplace.domain.party.PartyType; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
interface SpringDataPartyRepository extends JpaRepository<PartyJpaEntity,Long>{ Optional<PartyJpaEntity> findByIdAndType(Long id,PartyType type); List<PartyJpaEntity> findByTypeAndActiveTrueOrderByNameAsc(PartyType type); }
