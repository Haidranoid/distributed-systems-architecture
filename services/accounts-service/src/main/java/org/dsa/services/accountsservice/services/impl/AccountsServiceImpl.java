package org.dsa.services.accountsservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.accountsservice.common.dtos.AccountDto;
import org.dsa.services.accountsservice.common.dtos.VerifyAccountCredentialsDto;
import org.dsa.services.accountsservice.common.dtos.CreateAccountDto;
import org.dsa.services.accountsservice.common.dtos.UpdateAccountDto;
import org.dsa.services.core.servicesstarter.exceptions.AccountNotFoundException;
import org.dsa.services.core.servicesstarter.exceptions.UnauthorizedException;
import org.dsa.services.accountsservice.mappers.AccountsMapper;
import org.dsa.services.accountsservice.repositories.AccountsRepository;
import org.dsa.services.accountsservice.services.AccountsService;
import org.dsa.services.core.servicesstarter.utils.SessionUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final AccountsMapper accountsMapper;
    private final SessionUtils sessionUtils;

    public AccountDto me() {
        var username = sessionUtils.getCurrentUsername();

        if (username == null) {
            throw new UnauthorizedException();
        }

        var account = accountsRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("username " + username));

        return accountsMapper.toDto(account);
    }

    public AccountDto verifyCredentials(VerifyAccountCredentialsDto verifyAccountCredentialsDto) {
        var account = accountsRepository.findByUsername(verifyAccountCredentialsDto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return accountsMapper.toDto(account);
    }

    @Override
    public AccountDto create(CreateAccountDto createAccountDto) {
        //TODO: handle the case when the account already exist
        var accountEntity = accountsMapper.toEntity(createAccountDto);
        var accountSaved = accountsRepository.save(accountEntity);

        return accountsMapper.toDto(accountSaved);
    }

    @Override
    public void delete(Long userId) {
        var account = accountsRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("id " + userId));

        accountsRepository.delete(account);
    }

    @Override
    public AccountDto findById(Long userId) {
        var account = accountsRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("id " + userId));

        return accountsMapper.toDto(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> findAll() {
        var accountsList = accountsRepository.findAll().stream().map(accountsMapper::toDto).toList();

        return accountsList;
    }

    @Override
    public AccountDto update(Long userId, UpdateAccountDto updateAccountDto) {
        var account = accountsRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        //applyUpdates(accountDB, accountDto);

        var accountUpdated = accountsRepository.save(account);

        return accountsMapper.toDto(accountUpdated);
    }
}
