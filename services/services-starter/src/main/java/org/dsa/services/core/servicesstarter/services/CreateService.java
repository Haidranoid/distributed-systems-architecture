package org.dsa.services.core.servicesstarter.services;

public interface CreateService<R, C> {
    R create(C dto);
}
