package org.dsa.services.core.servicesstarter.services;

public interface BaseService<R, ID> extends ReadOperationService<R, ID>,
        CreateOperationService
        DeleteOperationService<ID> {
}
