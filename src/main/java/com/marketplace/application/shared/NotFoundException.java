package com.marketplace.application.shared;

public class NotFoundException extends RuntimeException { public NotFoundException(String resource, Long id) { super(resource + " com id " + id + " não encontrado"); } }
