package org.dsa.services.core.servicesstarter.services;

import org.dsa.services.core.servicesstarter.services.operations.CreateOperation;
import org.dsa.services.core.servicesstarter.services.operations.DeleteOperation;
import org.dsa.services.core.servicesstarter.services.operations.ReadOperation;
import org.dsa.services.core.servicesstarter.services.operations.UpdateOperation;

public interface BaseService<ID, R, CP, UP> extends
        CreateOperation<R, CP>,
        ReadOperation<R, ID>,
        UpdateOperation<R, UP, ID>,
        DeleteOperation<ID> {
}
