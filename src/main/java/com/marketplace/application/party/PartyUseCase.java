package com.marketplace.application.party;
import com.marketplace.domain.party.PartyType; import java.time.Instant; import java.util.List;
public interface PartyUseCase { View create(PartyType type,Command c); View update(Long id,PartyType type,Command c); View get(Long id,PartyType type); List<View> list(PartyType type); void deactivate(Long id,PartyType type); record Command(String name,String email,String document,boolean active){} record View(Long id,PartyType type,String name,String email,String document,boolean active,Instant createdAt){} }
