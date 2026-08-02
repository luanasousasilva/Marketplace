package com.marketplace.adapters.out.persistence.party;
import com.marketplace.domain.party.PartyType; import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="parties") class PartyJpaEntity { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Enumerated(EnumType.STRING) @Column(nullable=false) PartyType type; @Column(nullable=false,length=120) String name; @Column(nullable=false,length=254) String email; @Column(nullable=false,length=30) String document; @Column(nullable=false) boolean active; @Column(nullable=false,updatable=false) Instant createdAt; }
