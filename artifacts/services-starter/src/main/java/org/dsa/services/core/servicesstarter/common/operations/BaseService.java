package org.dsa.services.core.servicesstarter.common.operations;

public interface BaseService<ID, R, CP, UP> extends
        CreateOperation<R, CP>,
        ReadOperation<R, ID>,
        UpdateOperation<R, UP, ID>,
        DeleteOperation<ID> {
}
