package org.dsa.core.sharedstarter.common.operations;

public interface CreateOperation<R, CP> {
    R create(CP createPayload);
}
