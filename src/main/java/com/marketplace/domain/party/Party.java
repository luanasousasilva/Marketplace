package com.marketplace.domain.party;
import com.marketplace.domain.shared.DomainException;
import java.time.Instant;
public class Party {
    private final Long id; private final PartyType type; private String name; private String email; private String document; private boolean active; private final Instant createdAt;
    private Party(Long id,PartyType type,String name,String email,String document,boolean active,Instant createdAt){this.id=id;this.type=type;this.name=required(name,"Nome");this.email=required(email,"E-mail");this.document=required(document,"Documento");this.active=active;this.createdAt=createdAt;}
    public static Party create(PartyType type,String name,String email,String document){return new Party(null,type,name,email,document,true,Instant.now());}
    public static Party restore(Long id,PartyType type,String name,String email,String document,boolean active,Instant createdAt){return new Party(id,type,name,email,document,active,createdAt);}
    public void update(String name,String email,String document,boolean active){this.name=required(name,"Nome");this.email=required(email,"E-mail");this.document=required(document,"Documento");this.active=active;}
    private static String required(String value,String label){if(value==null||value.isBlank())throw new DomainException(label+" é obrigatório");return value.trim();}
    public Long id(){return id;} public PartyType type(){return type;} public String name(){return name;} public String email(){return email;} public String document(){return document;} public boolean active(){return active;} public Instant createdAt(){return createdAt;}
}
