package org.dsa.services.core.servicesstarter.services;

public interface EntityValidator<T> {
    boolean validateEntity(T entity);
}

