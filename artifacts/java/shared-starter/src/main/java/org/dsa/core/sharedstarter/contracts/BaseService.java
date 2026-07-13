package org.dsa.core.sharedstarter.contracts;

import java.util.List;

public interface BaseService<ID, RP, CP, UP> {
  // Create
  RP create(CP createPayload);

  // Read
  RP findById(ID id);

  List<RP> findAll();

  // Collection<R> findAll();
  // Page<R> findAll(Pageable pageable);

  // Update
  RP update(ID id, UP updatePayload);

  // Delete
  void delete(ID id);
}
