package org.dsa.services.authenticationservice.repositories;

import org.dsa.services.authenticationservice.common.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);
    List<TokenEntity> findAllValidTokensByAccountId(Long accountId);
}

