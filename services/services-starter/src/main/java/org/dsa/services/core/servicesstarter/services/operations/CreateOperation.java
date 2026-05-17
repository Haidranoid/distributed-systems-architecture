package org.dsa.services.core.servicesstarter.services.operations;

public interface CreateOperation<R, CP> {
    R create(CP createPayload);
}
