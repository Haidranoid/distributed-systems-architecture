package org.dsa.services.accountsservice.repository;

import java.util.Optional;
import org.dsa.services.accountsservice.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity, Long> {
  Optional<AccountEntity> findByUsername(String username);

  Optional<AccountEntity> findByEmail(String email);
}
