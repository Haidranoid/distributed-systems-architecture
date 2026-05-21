package org.dsa.services.core.servicesstarter.common.operations;

public interface UpdateOperation<R, UP, ID> {
    R update(ID id, UP updatePayload);
}
