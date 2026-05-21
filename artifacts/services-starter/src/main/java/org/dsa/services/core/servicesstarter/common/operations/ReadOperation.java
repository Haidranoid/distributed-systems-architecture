package org.dsa.services.core.servicesstarter.common.operations;

import java.util.List;

public interface ReadOperation<R, ID> {
    R findById(ID id);
    List<R> findAll();
    //Collection<R> findAll();
    //Page<R> findAll(Pageable pageable);
}
