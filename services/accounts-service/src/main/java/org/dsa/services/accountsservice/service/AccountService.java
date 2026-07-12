package org.dsa.services.accountsservice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.common.exceptions.AccountNotFoundException;
import org.dsa.core.sharedstarter.common.operations.BaseService;
import org.dsa.services.accountsservice.dto.AccountDto;
import org.dsa.services.accountsservice.dto.CreateAccountDto;
import org.dsa.services.accountsservice.dto.UpdateAccountDto;
import org.dsa.services.accountsservice.dto.VerifyAccountCredentialsDto;
import org.dsa.services.accountsservice.mapper.AccountMapper;
import org.dsa.services.accountsservice.repository.AccountRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements BaseService<Long, AccountDto, CreateAccountDto, UpdateAccountDto> {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  public AccountDto create(CreateAccountDto createAccountDto) {
    // TODO: handle the case when the account already exist
    var accountEntity = accountMapper.toEntity(createAccountDto);
    var accountSaved = accountRepository.save(accountEntity);

    return accountMapper.toDto(accountSaved);
  }

  public AccountDto findById(Long userId) {
    var account =
        accountRepository
            .findById(userId)
            .orElseThrow(() -> new AccountNotFoundException("id " + userId));

    return accountMapper.toDto(account);
  }

  @Transactional(readOnly = true)
  public List<AccountDto> findAll() {
    var accountsList = accountRepository.findAll().stream().map(accountMapper::toDto).toList();

    return accountsList;
  }

  public AccountDto update(Long userId, UpdateAccountDto updateAccountDto) {
    var account =
        accountRepository
            .findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

    // applyUpdates(accountDB, accountDto);

    var accountUpdated = accountRepository.save(account);

    return accountMapper.toDto(accountUpdated);
  }

  public void delete(Long userId) {
    var account =
        accountRepository
            .findById(userId)
            .orElseThrow(() -> new AccountNotFoundException("id " + userId));

    accountRepository.delete(account);
  }

  public AccountDto verifyCredentials(VerifyAccountCredentialsDto verifyAccountCredentialsDto) {
    var account =
        accountRepository
            .findByUsername(verifyAccountCredentialsDto.username())
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    return accountMapper.toDto(account);
  }
}
