package org.dsa.services.core.servicesstarter.services;

public interface UpdateService<R, U, ID> {
    R update(ID id, U dto);
}
