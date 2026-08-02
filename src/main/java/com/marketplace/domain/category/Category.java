package com.marketplace.domain.category;

import com.marketplace.domain.shared.DomainException;
import java.time.Instant;

public class Category {
    private final Long id; private String name; private String description; private boolean active; private final Instant createdAt;
    private Category(Long id, String name, String description, boolean active, Instant createdAt) { this.id=id; this.name=required(name); this.description=description; this.active=active; this.createdAt=createdAt; }
    public static Category create(String name, String description) { return new Category(null,name,description,true,Instant.now()); }
    public static Category restore(Long id,String name,String description,boolean active,Instant createdAt) { return new Category(id,name,description,active,createdAt); }
    public void update(String name,String description,boolean active) { this.name=required(name); this.description=description; this.active=active; }
    private static String required(String name) { if(name==null||name.isBlank()) throw new DomainException("Nome da categoria é obrigatório"); return name.trim(); }
    public Long id(){return id;} public String name(){return name;} public String description(){return description;} public boolean active(){return active;} public Instant createdAt(){return createdAt;}
}
