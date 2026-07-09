package org.dsa.services.accountsservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.common.exceptions.AccountNotFoundException;
import org.dsa.core.sharedstarter.common.exceptions.UnauthorizedException;
import org.dsa.core.sharedstarter.utils.SessionService;
import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.mappers.AccountMapper;
import org.dsa.services.accountsservice.repositories.AccountsRepository;
import org.dsa.services.accountsservice.services.MeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MeServiceImpl implements MeService {

    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final SessionService sessionService;

    @Override
    public AccountDto me() {
        var username = sessionService.getCurrentUsername();


        if (username == null) {
            throw new UnauthorizedException();
        }

        var account = accountsRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("username " + username));

        return accountMapper.toDto(account);
    }
}
