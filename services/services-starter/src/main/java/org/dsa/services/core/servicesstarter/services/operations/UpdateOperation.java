package org.dsa.services.core.servicesstarter.services.operations;

public interface UpdateOperation<R, UP, ID> {
    R update(ID id, UP updatePayload);
}
