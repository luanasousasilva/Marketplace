package com.marketplace.domain.party;
import java.util.*;
public interface PartyRepository { Party save(Party party); Optional<Party> findByIdAndType(Long id, PartyType type); List<Party> findActiveByType(PartyType type); }
