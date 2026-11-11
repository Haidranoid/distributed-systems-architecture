package org.dsa.services.accountsservice.repository;

import org.dsa.services.accountsservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByUsername(String username);

  Optional<Account> findByEmail(String email);
}
