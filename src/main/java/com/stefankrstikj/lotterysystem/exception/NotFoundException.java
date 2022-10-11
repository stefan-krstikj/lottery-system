package com.stefankrstikj.lotterysystem.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class clazz, Long id) {
        super(String.format("Entity of type %s with id %d not found", clazz.getSimpleName(), id));
    }

    public NotFoundException(Class clazz, UUID id) {
        super(String.format("Entity of type %s with id %s not found", clazz.getSimpleName(), id));
    }

    public NotFoundException() {
        super("Entity not found");
    }
}
