package org.dsa.services.accountsservice.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.common.exceptions.AccountNotFoundException;
import org.dsa.services.accountsservice.dto.AccountDto;
import org.dsa.services.accountsservice.dto.CreateAccountDto;
import org.dsa.services.accountsservice.dto.UpdateAccountDto;
import org.dsa.services.accountsservice.dto.VerifyAccountCredentialsDto;
import org.dsa.services.accountsservice.mapper.AccountMapper;
import org.dsa.services.accountsservice.repository.AccountsRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

  private final AccountsRepository accountsRepository;
  private final AccountMapper accountMapper;

  public AccountDto create(CreateAccountDto createAccountDto) {
    // TODO: handle the case when the account already exist
    var accountEntity = accountMapper.toEntity(createAccountDto);
    var accountSaved = accountsRepository.save(accountEntity);

    return accountMapper.toDto(accountSaved);
  }

  public AccountDto findById(Long userId) {
    var account =
        accountsRepository
            .findById(userId)
            .orElseThrow(() -> new AccountNotFoundException("id " + userId));

    return accountMapper.toDto(account);
  }

  @Transactional(readOnly = true)
  public List<AccountDto> findAll() {
    var accountsList = accountsRepository.findAll().stream().map(accountMapper::toDto).toList();

    return accountsList;
  }

  public AccountDto update(Long userId, UpdateAccountDto updateAccountDto) {
    var account =
        accountsRepository
            .findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

    // applyUpdates(accountDB, accountDto);

    var accountUpdated = accountsRepository.save(account);

    return accountMapper.toDto(accountUpdated);
  }

  public void delete(Long userId) {
    var account =
        accountsRepository
            .findById(userId)
            .orElseThrow(() -> new AccountNotFoundException("id " + userId));

    accountsRepository.delete(account);
  }

  public AccountDto verifyCredentials(VerifyAccountCredentialsDto verifyAccountCredentialsDto) {
    var account =
        accountsRepository
            .findByUsername(verifyAccountCredentialsDto.username())
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    return accountMapper.toDto(account);
  }
}
