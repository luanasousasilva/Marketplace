package com.marketplace.application.category;
import java.time.Instant; import java.util.List;
public interface CategoryUseCase { View create(Command c); View update(Long id,Command c); View get(Long id); List<View> list(); void deactivate(Long id); record Command(String name,String description,boolean active){} record View(Long id,String name,String description,boolean active,Instant createdAt){} }
