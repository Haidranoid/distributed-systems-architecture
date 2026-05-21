package org.dsa.services.core.servicesstarter.common.operations;

public interface CreateOperation<R, CP> {
    R create(CP createPayload);
}
