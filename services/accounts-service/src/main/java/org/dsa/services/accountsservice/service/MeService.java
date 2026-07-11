package org.dsa.services.accountsservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.common.exceptions.AccountNotFoundException;
import org.dsa.core.sharedstarter.common.exceptions.UnauthorizedException;
import org.dsa.core.sharedstarter.utils.SessionService;
import org.dsa.services.accountsservice.dto.AccountDto;
import org.dsa.services.accountsservice.mapper.AccountMapper;
import org.dsa.services.accountsservice.repository.AccountsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MeService {

  private final AccountsRepository accountsRepository;
  private final AccountMapper accountMapper;
  private final SessionService sessionService;

  public AccountDto me() {
    var username = sessionService.getCurrentUsername();

    if (username == null) {
      throw new UnauthorizedException();
    }

    var account =
        accountsRepository
            .findByUsername(username)
            .orElseThrow(() -> new AccountNotFoundException("username " + username));

    return accountMapper.toDto(account);
  }
}
