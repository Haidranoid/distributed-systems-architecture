package org.dsa.core.sharedstarter.common.operations;

public interface UpdateOperation<R, UP, ID> {
    R update(ID id, UP updatePayload);
}
