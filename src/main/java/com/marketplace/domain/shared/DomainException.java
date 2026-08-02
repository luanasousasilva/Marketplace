package com.marketplace.domain.shared;

/** Base exception for violated business rules. */
public class DomainException extends RuntimeException {
    public DomainException(String message) { super(message); }
}
