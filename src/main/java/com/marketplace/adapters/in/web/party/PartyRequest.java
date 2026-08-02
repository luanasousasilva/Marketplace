package com.marketplace.adapters.in.web.party;
import jakarta.validation.constraints.*;
public record PartyRequest(@NotBlank @Size(max=120) String name,@NotBlank @Email @Size(max=254) String email,@NotBlank @Size(max=30) String document,Boolean active){}
