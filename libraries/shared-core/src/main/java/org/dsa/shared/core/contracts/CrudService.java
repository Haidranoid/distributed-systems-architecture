package org.dsa.shared.core.contracts;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface CrudService<ID, RP, CP, UP> {
  // Create
  RP create(CP createPayload);

  // Read
  @Transactional(readOnly = true)
  RP findById(ID id);

  @Transactional(readOnly = true)
  List<RP> findAll();

  // Collection<R> findAll();
  // Page<R> findAll(Pageable pageable);

  // Update
  RP update(ID id, UP updatePayload);

  // Delete
  void delete(ID id);
}
