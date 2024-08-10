package org.chulgang.hrd.exception;

public abstract class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
