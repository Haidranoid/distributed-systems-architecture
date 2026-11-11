package org.dsa.services.authenticationservice.repository;

import org.dsa.services.authenticationservice.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
  Optional<Token> findByToken(String token);

  List<Token> findAllValidTokensByAccountId(Long accountId);
}
