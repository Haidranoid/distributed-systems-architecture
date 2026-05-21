package org.dsa.core.sharedstarter.common.operations;

public interface BaseService<ID, R, CP, UP> extends
        CreateOperation<R, CP>,
        ReadOperation<R, ID>,
        UpdateOperation<R, UP, ID>,
        DeleteOperation<ID> {
}
